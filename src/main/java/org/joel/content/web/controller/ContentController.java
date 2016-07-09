package org.joel.content.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.joel.content.constants.UserType;
import org.joel.content.dto.AsynExecResult;
import org.joel.content.dto.BuyInfo;
import org.joel.content.dto.UserData;
import org.joel.content.entity.Content;
import org.joel.content.entity.Person;
import org.joel.content.entity.Trx;
import org.joel.content.enums.StateEnum;
import org.joel.content.exception.TrxException;
import org.joel.content.exception.TrxProductException;
import org.joel.content.service.ContentService;
import org.joel.content.service.TrxService;
import org.joel.content.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ContentController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrxService trxservice;
	
	/*
	 * 获取发布产品的页面
	 */
	@RequestMapping(
			value="/public",
			method=RequestMethod.GET
			)
	public String getPublicPage(){
		return "public";
	}
	
	/**
	 * 提交卖家发布的产品数据
	 * @param content
	 * @param map
	 * @return
	 */
	@RequestMapping(
			value="/publicSubmit",
			method=RequestMethod.POST
			)
	public String publicSubmit(Content content, HttpServletRequest request, ModelMap map){
		HttpSession session = request.getSession();
		UserData user = (UserData)session.getAttribute("user");
		if(user == null || user.getUsertype() != UserType.SELLER){
			logger.error("不合法用户企图发布产品");
			return "publicSubmit";
		}
		
		int insertCnt = contentService.insertContent(content);
		if(insertCnt > 0){
			map.addAttribute("product", content);
		}
		
		return "publicSubmit";
	}
	
	/**
	 * 获取指定产品的编辑页面
	 * @param id 产品id
	 * @param map
	 * @return
	 */
	@RequestMapping(
			value="/edit",
			method=RequestMethod.GET
			)
	public String getEditPage(@RequestParam("id") int id, ModelMap map){
		Content content = contentService.getContentById(id);
		map.addAttribute("product", content);
		return "edit";
	}
	
	/**
	 * 提交卖家编辑后的产品数据
	 * @param content
	 * @param map
	 * @return
	 */
	@RequestMapping(
			value="/editSubmit",
			method=RequestMethod.POST
			)
	public String editSubmit(Content content, HttpServletRequest request, ModelMap map){
		HttpSession session = request.getSession();
		UserData user = (UserData)session.getAttribute("user");
		if(user == null || user.getUsertype() != UserType.SELLER){
			logger.error("不合法用户企图修改产品数据");
			return "editSubmit";
		}
		
		int updateCnt = contentService.updateContent(content);
		if(updateCnt > 0){
			//数据更新成功
			map.addAttribute("product", content);
		}
		
		return "editSubmit";
	}

	
	/**
	 * 获取产品详细信息
	 * @param id
	 */
	@RequestMapping(
			value="/show",
			method=RequestMethod.GET
			)
	public String showProduct(@RequestParam("id") int id, HttpServletRequest request, ModelMap map){
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		UserData user = (UserData)session.getAttribute("user");
		Short userType;
		if(user == null){
			userType = null;
		}else{
			userType = user.getUsertype();
		}
		
		Content content = contentService.getDetailByContentId(id, userId, userType);
		map.addAttribute("product", content);
		return "show";
	}
	
	/**
	 * 获取当前买家的账务信息
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(
			value="/account",
			method=RequestMethod.GET
			)
	public String getAccount(HttpServletRequest request, ModelMap map){
		//从会话区获取当前用户Id
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId != null){
			//登录用户请求账务信息
			List<Trx> list = trxservice.getAccountForBuyer(userId);
			map.addAttribute("buyList", list);
		}
		
		return "account";
	}
	
	/**
	 * 登录页面请求
	 * @return
	 */
	@RequestMapping(
			value="/login",
			method=RequestMethod.GET
			)
	public String getLoginPage(){
		return "login";
	}
	
	/**
	 * 用户登录处理
	 * @param request
	 * @return
	 */
	@RequestMapping(
			value="/api/login",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"}
			)
	@ResponseBody
	public AsynExecResult<Boolean> loginSubmit(HttpServletRequest request){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		/* 用户名或密码为空 */
		if (userName == null || password == null){
			return new AsynExecResult<Boolean>(StateEnum.DATA_INVALID, false);
		}
		
		Person person = userService.getUser(userName, password);
		/* 根据用户名和密码没有查到用户信息 */
		if (person == null){
			return new AsynExecResult<Boolean>(StateEnum.NOT_REGISTER, false);
		}
		
		/* 查询到匹配的用户信息 */
		HttpSession session = request.getSession();
		/* 用UserData对象存储要传输给前端页面的用户信息, 并将其保存在会话中 */
		UserData user = new UserData();
		user.setUsername(person.getNickName());
		user.setUsertype(person.getUserType());
		session.setAttribute("user", user);
		session.setAttribute("userId", person.getId());
		return new AsynExecResult<Boolean>(StateEnum.SUCCESS, true);
	}
	
	@RequestMapping(
			value="/api/delete",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"}
			)
	@ResponseBody
	public AsynExecResult<Boolean> deleteContent(@RequestParam("id") int id, HttpServletRequest request){
		HttpSession session = request.getSession();
		UserData user = (UserData)session.getAttribute("user");
		if(user == null || user.getUsertype() != UserType.SELLER){
			//未授权用户不能删除产品数据
			logger.error("不合法用户企图删除产品数据");
			return new AsynExecResult<Boolean>(StateEnum.WITHOUT_AUTHORIZATION,false);
		}
		
		int deleteCnt = contentService.deleteContent(id);
		if(deleteCnt < 1){
			//删除失败
			return new AsynExecResult<Boolean>(StateEnum.DB_DELETE_FAILURE,false);
		}else{
			//删除成功
			return new AsynExecResult<Boolean>(StateEnum.SUCCESS,true);
		}
	}
	
	/**
	 * 记录用户购买产品数据
	 * @param buyList
	 * @param request
	 * @return
	 */
	@RequestMapping(
			value="/api/buy",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"}
			)
	@ResponseBody
	public AsynExecResult<Boolean> buyContents(@RequestBody List<BuyInfo> buyList, HttpServletRequest request){
		//从会话区获取当前用户Id
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null){
			//用户未登录
			return new AsynExecResult<Boolean>(StateEnum.NOT_LOGGEDIN, false);
		}
		
		try {
			boolean isSuccess = trxservice.addTrx(buyList, userId);
			if(isSuccess){
				return new AsynExecResult<Boolean>(StateEnum.SUCCESS, true);
			}else{
				return new AsynExecResult<Boolean>(StateEnum.UNKNOWN, false);
			}
		} catch (TrxProductException e) {
			return new AsynExecResult<Boolean>(StateEnum.PRODUCT_NOT_EXIST, false);
		} catch (TrxException e) {
			return new AsynExecResult<Boolean>(StateEnum.INNER_ERROR,false);
		}
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(
			value="/logout",
			method=RequestMethod.GET
			)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);  //以false为参数，放置创建新的session
		if(session == null){
			return "redirect:/login";
		}
		
		session.removeAttribute("user");
		session.removeAttribute("userId");
		return "redirect:/login";
	}
	
	/**
	 * 获取内容列表
	 * @param map
	 * @return
	 */
	@RequestMapping(
			value="/",
			method=RequestMethod.GET
			)
	public String getContentList(HttpServletRequest request, ModelMap map){
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		UserData user = (UserData)session.getAttribute("user");
		Short userType;
		if(user == null){
			userType = null;
		}else{
			userType = user.getUsertype();
		}
		
		List<Content> productList = contentService.getContentList(userId, userType);
		map.addAttribute("productList", productList);
		return "index";
	}
	
	/**
	 * 获取购物车页面
	 * @return
	 */
	@RequestMapping(
			value="/settleAccount",
			method=RequestMethod.GET
			)
	public String getShoppingCartPage(){
		return "settleAccount";
	}
	
	/**
	 * 将客户端上传的文件保存到服务器文件夹中，并返回存储的相对路径给客户端
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(
			value="/api/upload",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"}
			)
	@ResponseBody
	public AsynExecResult<String> doUploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		//文件为空
		if (file.isEmpty()){
			response.setStatus(StateEnum.FILE_EMPTY.getCode());
			return new AsynExecResult<String>(StateEnum.FILE_EMPTY,null);
		}
		
		try {
			//获取web应用的根目录
			String path = request.getServletContext().getRealPath("/");
			//获取文件名
			String originFileName = file.getOriginalFilename();
			//获取文件扩展名
			String extensionName = originFileName.substring(originFileName.lastIndexOf(".")+1);
			//新的文件名
			String newFileName = getRandomStr() + "." + extensionName;
			//服务器中文件的全路径
			String filePath = path + File.separator + "image" + File.separator + newFileName;
			//返回给前端的文件相对路径
			String returnPath = File.separator + "image" + File.separator + newFileName;
			//将文件保存到服务器中
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
			
			response.setStatus(StateEnum.SUCCESS.getCode());
			return new AsynExecResult<String>(StateEnum.SUCCESS,returnPath);
		} catch (IOException e) {
			logger.error("文件上传失败");
			response.setStatus(StateEnum.FILE_UPLOAD_FAILURE.getCode());
			return new AsynExecResult<String>(StateEnum.FILE_UPLOAD_FAILURE,null);
		}
	}
	
	/**
	 * 用当前系统时间+5位随机数生成随机字符串
	 * @return
	 */
	private String getRandomStr(){
		//获取当前系统时间
		String time = String.valueOf(System.currentTimeMillis());
		//生成5位随机数
		Random random = new Random();
		int ranNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;  
		
		return time + ranNum;
	}
	
}
