package cn.czcxy.xj.basicserver.menu.model;

import cn.czcxy.xj.core.util.tree.BaseTree;

import java.util.List;

/**
 * @Auther: wwh
 * @Date: 2018/11/25 0025 16:41
 * @Description:
 */
public class EidpMenuTree extends EidpMenu implements BaseTree<EidpMenuTree, Integer> {

    private List<EidpMenuTree> children;

    private boolean hasChild;

    @Override
    public Integer getPid() {
        return super.getParentId();
    }

    @Override
    public void setChildren(List<EidpMenuTree> e) {
        this.children = e;
    }

    @Override
    public List<EidpMenuTree> getChildren() {
        return this.children;
    }

    @Override
    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    @Override
    public boolean getHasChild() {
        return this.hasChild;
    }
}
