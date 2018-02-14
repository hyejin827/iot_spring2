package com.iot.spring.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

public interface ConnectionInfoService {

	public List<ConnectionInfoVO> getConnectionInfoList(ConnectionInfoVO ci);
	public void insertConnectionInfo(Map<String,Object> rMap, ConnectionInfoVO ci);
	public List<Map<String, Object>> getDatabaseList(HttpSession hs, int ciNo) throws Exception;
	List<TableVO> getTableList(HttpSession hs, String dbName);
	List<ColumnVO> getColumnList(String dbName);
}
