package org.joel.content.service.impl;

import java.util.List;

import org.joel.content.constants.UserType;
import org.joel.content.dao.ContentDao;
import org.joel.content.entity.Content;
import org.joel.content.entity.ContentWithTrx;
import org.joel.content.entity.Trx;
import org.joel.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contentService")
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentDao contentDao;

	@Override
	public List<Content> getContentList(Integer userId, Short userType) {
		if(userId == null || userType == null){
			//用户未登录时，查询产品列表
			return contentDao.queryAll();
		}
		
		if(userType == UserType.BUYER){
			List<Content> list = contentDao.queryAllForBuyer(userId);
			return list;
		}else if(userType == UserType.SELLER){
			return  contentDao.queryAllForSeller();
		}
		
		return null;   
	}

	@Override
	public Content getDetailByContentId(int contentId, Integer userId, Short userType) {
		if(userId == null || userType == null){
			//用户未登录时,查询产品详情
			return contentDao.queryById(contentId);
		}
		
		if(userType == UserType.BUYER){
			//买家查询产品详情
			return contentDao.queryByIdForBuyer(contentId, userId);
		}else if(userType == UserType.SELLER){
			//卖家查询产品详情
			ContentWithTrx contentWithTrx = contentDao.queryByIdForSeller(contentId);
			if(contentWithTrx == null)
			{
				//没有查到该产品的数据
				return null;
			}else{
				Content content = new Content();
				content.setId(contentWithTrx.getId());
				content.setTitle(contentWithTrx.getTitle());
				content.setSummary(contentWithTrx.getSummary());
				content.setImage(contentWithTrx.getImage());
				content.setDetail(contentWithTrx.getDetail());
				content.setPrice(contentWithTrx.getPrice());
				List<Trx> trxList = contentWithTrx.getTrxes();
				if(trxList == null){
					//在交易表中没有查到该产品的交易记录
					content.setIsSell(false);
				}else{
					//在交易表中查到了该产品的交易记录
					int saleNum = 0;
					for(Trx trx : trxList){
						System.out.println();
						saleNum += trx.getBuyNum();
					}
					if(saleNum == 0){
						//如果销售数为0，说明都是些无效的销售数据
						content.setIsSell(false);
					}else{
						content.setIsSell(true);
						content.setSaleNum(saleNum);
					}
				}
				return content;
			}
		}
		return null;  
	}

	@Override
	public int insertContent(Content content) {
		if(content == null){
			//没有待插入的产品
			return 0;
		}
		return contentDao.insertContent(content);
	}

	@Override
	public Content getContentById(int contentId) {
		return contentDao.queryById(contentId);
	}

	@Override
	public int updateContent(Content content) {
		if(content == null){
			//没有待更新的产品
			return 0;
		}
		return contentDao.updateContent(content);
	}

	@Override
	public int deleteContent(int contentId) {
		return contentDao.deleteContent(contentId);
	}

}