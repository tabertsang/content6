package org.joel.content.service.impl;

import java.util.Date;
import java.util.List;

import org.joel.content.dao.ContentDao;
import org.joel.content.dao.TransactionDao;
import org.joel.content.dto.BuyInfo;
import org.joel.content.entity.Content;
import org.joel.content.entity.Trx;
import org.joel.content.exception.TrxException;
import org.joel.content.exception.TrxProductException;
import org.joel.content.service.TrxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("trxService")
public class TrxServiceImpl implements TrxService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private ContentDao contentDao;

	@Override
	@Transactional
	public boolean addTrx(List<BuyInfo> list, int personId) throws TrxException, TrxProductException {
		//获取交易时间
		Date nowTime = new Date();
		long time = nowTime.getTime();
		
		
		try {
			for(BuyInfo buyInfo : list){
				Trx trx = new Trx();
				//根据内容ID，查询内容信息
				Content content = contentDao.queryById(buyInfo.getId());
				if(content == null){
					throw new TrxProductException("product id does not exist");
				}
				
				trx.setContentId(content.getId());
				trx.setBuyPrice(content.getPrice());
				trx.setPersonId(personId);
				trx.setBuyNum(buyInfo.getNumber());
				trx.setBuyTime(time);
				transactionDao.insertTransaction(trx);
			}
		} catch(TrxProductException e){
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new TrxException("transaction innner error:" + e.getMessage());
		}
		
		return true;
	}

	@Override
	public List<Trx> getAccountForBuyer(int personId) {
		
		return transactionDao.getAccountByPersonId(personId);
	}

}
