package com.my.jpa.dao;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.jpa.dto.StoreDTO;
import com.my.jpa.entity.StoreEntity;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class StoreEntityTest {
	@Autowired
	StoreEntityRepository r;

	@Test
	void test1Insert() {
		StoreEntity s = 
				StoreEntity
				.builder()
				.corNo("사업자번호1")
				.name("음식점1")
				.build();
		log.error("INSERT용 엔티티객체 사업자번호: {}, 상호명: {}", s.getCorNo(), s.getName());
		r.save(s);
	}
	
	@Test
	void test2Update() {
		String corNo = "사업자번호1";
		Optional<StoreEntity> optS = r.findById(corNo);
		StoreEntity s = optS.get();
		s.modifyName("다른음식점");
		r.save(s); //save메서드 하나만 호출하면 auto commit을 하기 때문에 @Transactional @Commit 필요가 없다
				//그러나, 여러 메서드를 호출할 때는 각각 commit되기 때문에 오버헤드 발생-> 한꺼번에 처리하기 위해 어노테이션 사용
	}
	
	@Test
	void test3Delete() {
		String corNo = "사업자번호1";
		r.deleteById(corNo);
	}
	
	@Test
	void test4DtoToEntity() { //DTO->VO (직접 설정)
		StoreDTO dto = StoreDTO
						.builder()
						.corNo("사업자번호2")
						.name("음식점2")
						.dt(new java.util.Date())
						.build();
		
		StoreEntity entity = StoreEntity
								.builder()
								.corNo(dto.getCorNo())
								.name(dto.getName())
								.dt(new java.sql.Date(dto.getDt().getTime()))
								.build();
	}
	
	@Test //ModelMapper활용 DTO->VO
	void test5DtoToVo_ModelMapper() {
		StoreDTO dto = StoreDTO
				.builder()
				.corNo("사업자번호2")
				.name("음식점2")
				.dt(new java.util.Date())
				.build();
		
//		StoreDTO dto = new StoreDTO();
//		dto.setCorNo("사업자번호2");
//		dto.setName("음식점2");
//		dto.setDt(new java.util.Date());
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		
		Object source = dto; //인자1
		Class<StoreEntity> destinationType = StoreEntity.class; //인자2
		StoreEntity entity = mapper.map(source, destinationType); //DTO->VO (destinationType으로 full mapped)
		log.error("entity corNo: {}, name: {}, dt: {}", 
				entity.getCorNo(),
				entity.getName(),
				entity.getDt());
	}
	
	@Test //ModelMapper활용 VO->DTO
	void test6VoToDto_ModelMapper() {
		String corNo = "사업자번호1";
		Optional<StoreEntity> optS = r.findById(corNo);
		StoreEntity sEntity = optS.get();
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		
		Object source = sEntity;
		Class<StoreDTO> destinationType = StoreDTO.class;
		StoreDTO dto = mapper.map(source, destinationType); //VO->DTO
//		StoreDTO dto = mapper
//						.typeMap(null, null) //멤버변수가 다른 경우
//						.addMapping(null, null);
		
		log.error("entity corNo: {}, name: {}, dt: {}", 
				dto.getCorNo(),
				dto.getName(),
				dto.getDt());
	}
	
	//그러나 ModelMapper를 사용하려면 DTO와 VO의 변수명이 동일해야한다! 동일하지 않으면 수동으로 맵핑해줘야 한다

}
