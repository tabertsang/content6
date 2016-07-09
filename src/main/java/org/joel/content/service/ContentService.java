package org.joel.content.service;

import java.util.List;

import org.joel.content.entity.Content;

public interface ContentService {
	/**
	 * 查询所有内容记录
	 * @param userId
	 * @param userType
	 * @return
	 */
	List<Content> getContentList(Integer userId, Short userType);
	
	/**
	 * 查询单个产品的相关详细信息
	 * @param contentId
	 * @param userId
	 * @param userType
	 * @return
	 */
	Content getDetailByContentId(int contentId, Integer userId, Short userType);
	
	/**
	 * 插入发布内容到内容表中
	 * @param content
	 * @return 插入的记录数
	 */
	int insertContent(Content content);
	
	/**
	 * 根据id获取发布的产品
	 * @param contentId
	 * @return
	 */
	Content getContentById(int contentId);
	
	/**
	 * 更新已发布的内容
	 * @param content
	 * @return 更新的记录数
	 */
	int updateContent(Content content);
	
	/**
	 * 删除指定产品
	 * @param contentId
	 * @return 影响的记录数
	 */
	int deleteContent(int contentId);
}
