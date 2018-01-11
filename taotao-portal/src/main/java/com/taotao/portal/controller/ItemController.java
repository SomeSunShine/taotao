package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * 商品信息服务
 * 
 * @ClassName: ItemController
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月18日 下午10:54:46
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	/**
	 * 获取基本信息
	 * 
	 * @Title: showItem
	 * @Description: TODO
	 * @param itemId
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		ItemInfo item = itemService.getItemById(itemId);

		model.addAttribute("item", item);
		return "item";
	}

	/**
	 * 获取详情信息
	 * 
	 * @Title: getItemDesc
	 * @Description: TODO
	 * @param itemId
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String string = itemService.getItemDescById(itemId);
		return string;
	}

	/**
	 * 获取规格参数
	 * 
	 * @Title: getItemParam
	 * @Description: TODO
	 * @param itemId
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String string = itemService.getItemParamById(itemId);
		return string;
	}

	public static void main(String[] args) {
		String str = "8746346746";
		char[] strArray = str.toCharArray();
		for (int i = 0; i < strArray.length - 1; i++) {
			for (int j = 0; j < strArray.length - 1 - i; j++) {
				if (strArray[j] < strArray[j + 1]) {
					char temp = strArray[j];
					strArray[j] = strArray[j + 1];
					strArray[j + 1] = temp;
				}
			}
		}
		System.out.println(strArray);
	}

}
