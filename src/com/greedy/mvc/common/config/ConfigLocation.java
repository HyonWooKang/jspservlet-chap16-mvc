package com.greedy.mvc.common.config;

public class ConfigLocation {

	/* 환경정보 값 받아와서 사용하는 클래스 */
	
	/*	static 변수로 만들어놓고 filter에서 경로가 비어있을 경우 자동으로 받아주게 설정해서
	 *  해당 변수에 담아줘서, 파일을 찾아갈 때마다 해당 변수를 사용한다.
	 */
	
	// DB 접속 정보를 담고 있는 connection-info.properties 파일의 경로를 담고 있는 static 변수
	public static String CONNECTION_CONFIG_LOCATION;
	
	// sql 구문이 작성되어 있는 xml 파일의 경로를 담고 있는 static 변수
	public static String MAPPER_LOCATION;
	
}
