package org.joel.content.dao;

import java.util.List;

import org.joel.content.entity.Trx;

public interface TransactionDao {
	/**
	 * 插入交易数据
	 * @param transaction
	 * @return
	 */
	public int insertTransaction(Trx transaction);
	
	/**
	 * 根据用户Id获取用户交易信息
	 * @param personId
	 * @return
	 */
	public List<Trx> getAccountByPersonId(int personId);
}
