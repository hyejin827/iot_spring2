package com.iot.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.ConnectionInfoService;
import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;
import com.iot.spring.vo.UserInfoVO;

@Controller
@RequestMapping("/connection")
public class ConnectionInfoController {
	
	private static final Logger log = LoggerFactory.getLogger(ConnectionInfoController.class);
	
	private ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private ConnectionInfoService cs;

	@RequestMapping("/list")
	public @ResponseBody Map<String,Object> getConnectionList(HttpSession hs, Map<String,Object> map){
		UserInfoVO ui = new UserInfoVO();
		if(hs.getAttribute("user")!=null){
			ui = (UserInfoVO)hs.getAttribute("user");
		}else {
			ui.setuId("red");
		}
		ConnectionInfoVO ci = new ConnectionInfoVO();
		ci.setuId(ui.getuId());
		List<ConnectionInfoVO> list = cs.getConnectionInfoList(ci);
		map.put("list", list);
		return map;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertConnection(@RequestParam Map<String,Object> map) {
		ConnectionInfoVO ci = om.convertValue(map, ConnectionInfoVO.class);
		log.info("ConnectionInfoVO => {}", ci);
		cs.insertConnectionInfo(map, ci);
		return map;
	}
	
	@RequestMapping(value="/db_list/{ciNo}", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getDatabaseList(@PathVariable("ciNo") int ciNo, Map<String,Object> map, HttpSession hs) {
		List<Map<String, Object>> dbList;
		try {
			dbList = cs.getDatabaseList(hs, ciNo);
			map.put("list", dbList);
			map.put("parentId", ciNo);
		}catch(Exception e){
			map.put("error", e.getMessage());
			log.error("db connection error => {}",e);
		}
		return map;
	}
	
	@RequestMapping(value="/tables/{dbName}/{parentId}", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getTableList(
			@PathVariable("dbName")String dbName, 
			@PathVariable("parentId") String parentId, 
			HttpSession hs,
			Map<String,Object> map) {
		log.info("dbName => {}, parentId => {}", new Object[] {dbName, parentId});
		List<TableVO> tableList = cs.getTableList(hs, dbName);
		map.put("list", tableList);
		map.put("parentId", parentId);
		return map;
	}
	
	@RequestMapping(value="/columns/{dbName}", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getColumnList(@PathVariable("dbName")String dbName, Map<String,Object> map) {
		List<ColumnVO> columnList = cs.getColumnList(dbName);
		map.put("dbList", columnList);
		return map;
	}
	
}
