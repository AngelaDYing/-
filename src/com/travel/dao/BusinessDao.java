package com.travel.dao;

import static com.travel.util.common.Constants.BUSINESSTABLE;
import static com.travel.util.common.Constants.STAFFTABLE;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.BusinessDynaSqlProvider;
import com.travel.domain.Business;

public interface BusinessDao {
	//����ȫ�������
	@Select("select * from "+BUSINESSTABLE+" ")
	List<Business> get_All_Business();
	//�鿴ȫ��Ա��δ����׼�������
	@Select("select * from "+BUSINESSTABLE+" where status=0")
	List<Business> get_Allnow_Business();
	//����ĳ��Ա������׼����׼�������
	@Select("select * from "+BUSINESSTABLE+" where id = #{id} AND status=1 OR status=2")
	List<Business> get_Business_pastList(String id);
	//����ĳ��Ա������״̬�е������
	@Select("select * from "+BUSINESSTABLE+" where id = #{id} AND status = 0")
	List<Business> get_Business_nowList(String id);
	//����ĳ�������
	@Select("select * from "+BUSINESSTABLE+"  where id = #{id} AND date = #{date}")
	Business get_Business_LikeList(@Param("id")String id,@Param("date")Timestamp date);
	//�½������
	@SelectProvider(type=BusinessDynaSqlProvider.class,method="insert_Business")
	void insert_Business(Business job);
	//Ա���޸�������޷��޸�״̬
	@SelectProvider(type=BusinessDynaSqlProvider.class,method="update_Business")
	void update_Business(Business job);
	//�����޸������ֻ���޸�״̬
	@SelectProvider(type=BusinessDynaSqlProvider.class,method="update_Business_status")
	void update_Business_status(Business job);
	// ��������ɾ�������
	@Delete(" delete from "+BUSINESSTABLE+" where id = #{id} AND date = #{date} ")
	void delete_Business(@Param("id")String id,@Param("date")Timestamp date);
	
	//������ԭ���õķ��������ڲ�����
	//����ѯ������ĳ������Ĳ��ŵ�����Ա�������
	//@Select("SELECT * from" +BUSINESSTABLE +"INNER JOIN" + STAFFTABLE + "")
	@Select("<script> SELECT  *  from  BUSINESSTABLE t" +
            " INNER JOIN STAFFTABLE t1 on t1.id = t.id "
            + " <if test=\"id != null and '' != id\"> and t1.apartment_id = #{apartment_id} </if> "
            + " </script>")
	List<Business> apartment_id_business(String apartment_id);
	
	//����ѯ������ĳ������Ĳ��ŵ������е�Ա�������
		//@Select("SELECT * from" +BUSINESSTABLE +"INNER JOIN" + STAFFTABLE + "")
		@Select("<script> SELECT  *  from  BUSINESSTABLE t" +
	            " INNER JOIN STAFFTABLE t1 on t1.id = t.id "
	            + " <if test=\"id != null and '' != id\"> and t1.apartment_id = #{apartment_id} </if> and status = 0 "
	            + " </script>")
		List<Business> apartment_id_nowbusiness(String apartment_id);
	

	

	

}


