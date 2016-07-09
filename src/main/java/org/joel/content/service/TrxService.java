package org.joel.content.service;

import java.util.List;

import org.joel.content.dto.BuyInfo;
import org.joel.content.entity.Trx;
import org.joel.content.exception.TrxException;
import org.joel.content.exception.TrxProductException;

public interface TrxService {
	/**
	 * 将用户购买的内容加入交易表中
	 * @param list
	 * @param personId
	 * @return
	 * @throws TrxException
	 */
	boolean addTrx(List<BuyInfo> list, int personId)
		throws TrxException, TrxProductException;
	
	/**
	 * 根据用户Id查询账务信息
	 * @param personId
	 * @return
	 */
	List<Trx> getAccountForBuyer(int personId);
}
