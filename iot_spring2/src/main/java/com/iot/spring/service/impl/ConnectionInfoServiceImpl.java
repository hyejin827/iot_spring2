package com.iot.spring.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.common.dbcon.DBConnector;
import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.service.ConnectionInfoService;
import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService {

	@Autowired
	private ConnectionInfoDAO cdao;
	
	@Override
	public List<ConnectionInfoVO> getConnectionInfoList(ConnectionInfoVO ci) {
		List<ConnectionInfoVO> result = cdao.selectConnectionInfoList(ci);
		return result;
	}
	
	@Override
	public void insertConnectionInfo(Map<String,Object> rMap, ConnectionInfoVO ci) {
		int result = cdao.insertConnectionInfo(ci);
		if(result==1) {
			rMap.put("msg", "성공! 룰루랄라"); 
		}else {
			rMap.put("msg", "실패!");
		}
	}

	@Override
	public List<Map<String, Object>> getDatabaseList(HttpSession hs, int ciNo) throws Exception {
		ConnectionInfoVO ci = cdao.selectConnectionInfo(ciNo);
		hs.setAttribute("ci", ci);
		DBConnector dbc = new DBConnector(ci);
		SqlSession ss = dbc.getSqlSession();
		hs.setAttribute("sqlSession", dbc.getSqlSession());
		List<Map<String,Object>> dbList = cdao.selectDatabaseList(ss);
		int idx = 0;
		for(Map<String,Object> mDb : dbList) {
			mDb.put("id", ciNo + "_" + (++idx));
			mDb.put("text", mDb.get("Database"));
			mDb.put("items", new Object[] {});
		}
		return dbList;
	}

	@Override
	public List<TableVO> getTableList(HttpSession hs, String dbName) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		return cdao.selectTableList(ss, dbName);
	}

	@Override
	public List<ColumnVO> getColumnList(String dbName) {
		return cdao.selectColumnList(dbName);
	}
}
