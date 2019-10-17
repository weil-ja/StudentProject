package com.taotao.manage.service;

import com.taotao.manage.pojo.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentCategoryService extends BaseService<ContentCategory> {
    /**
     * 保存
     *
     * @param contentCategory
     */
    public void saveContentCategory(ContentCategory contentCategory) {
        contentCategory.setId(null)
                .setIsParent(false)
                .setSortOrder(1)
                .setStatus(1);
        super.save(contentCategory);

        ContentCategory parent = super.queryById(contentCategory.getParentId());
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            super.update(parent);
        }
    }

    /**
     *删除节点
     *
     * @param contentCategory
     */
    public void deleteAll(ContentCategory contentCategory) {
        List<Object> ids = new ArrayList<Object>();
        //删除当前节点
        ids.add(contentCategory.getId());

        //递归查找该节点下的子节点
        this.findAllSubNode(ids, contentCategory.getId());

        super.deleteByIds(ids, ContentCategory.class, "id");

        //判断该节点是否有兄弟节点，没有修改父节点isparent为false
        ContentCategory record = new ContentCategory()
                .setParentId(contentCategory.getParentId());
        List<ContentCategory> list = super.queryListByWhere(record);
        if (list == null || list.isEmpty()) {
            ContentCategory parent=new ContentCategory()
                    .setId(contentCategory.getParentId())
                    .setIsParent(false);
            super.updateSelective(parent);
        }
    }

    /**
     * 提供查询所有子节点功能
     *
     * @param ids
     * @param pid
     */
    private void findAllSubNode(List<Object> ids, Long pid) {
        ContentCategory record = new ContentCategory()
                .setParentId(pid);
        List<ContentCategory> list = super.queryListByWhere(record);
        for (ContentCategory contentCategory : list) {
            ids.add(contentCategory.getId());
            //判断该节点是否为子节点，是则继续
            if (contentCategory.getIsParent()) {
                findAllSubNode(ids, contentCategory.getId());
            }
        }
    }
}
