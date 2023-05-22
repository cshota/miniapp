package jp.co.scsk.miniapp.sample.api.dao;

import jp.co.scsk.miniapp.sample.api.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthDao {

    void insert(Customer customer);

    void update(Customer customer);

    Customer getByUserId(long userId);

}
