package com.taotao.service;

import java.util.List;


import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.util.EasyUITreeNode;

public interface TbItemCatService extends BaseService<TbItemCat, TbItemCatExample, Long>{
	List<EasyUITreeNode> getItemCatList(long parentId);
}
