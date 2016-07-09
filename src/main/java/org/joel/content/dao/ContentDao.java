package org.joel.content.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.joel.content.entity.Content;
import org.joel.content.entity.ContentWithTrx;

public interface ContentDao {
	/**
	 * 将发布的内容插入
	 * @param content 插入的内容对象
	 * @return 插入的记录数
	 */
	public int insertContent(Content content);
	
	/**
	 * 更新发布的内容
	 * @param content
	 * @return 更新的记录数
	 */
	public int updateContent(Content content);
	
	/**
	 * 根据id查询内容
	 * @param id 
	 * @return
	 */
	public Content queryById(int id);
	
	/**
	 * 买家根据内容id查询内容
	 * @param id 
	 * @return
	 */
	public Content queryByIdForBuyer(@Param("id") int id, @Param("personId") int personId);
	
	/**
	 * 卖家根据内容id查询内容
	 * @param id 
	 * @return
	 */
	public ContentWithTrx queryByIdForSeller(int id);
	
	/**
	 * 查询所有内容
	 * @return
	 */
	public List<Content> queryAll();
	
	/**
	 * 为登录类型为买家的用户查询所有内容列表
	 * @param personId 买家用户的ID
	 * @return
	 */
	public List<Content> queryAllForBuyer(int personId);
	
	/**
	 * 为登录类型为卖家的用户查询所有内容列表
	 * @param 
	 * @return
	 */
	public List<Content> queryAllForSeller();
	
	/**
	 * 删除指定的产品数据
	 * @param contentId
	 * @return 影响的记录数
	 */
	public int deleteContent(int contentId);
	
}
