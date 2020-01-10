package cn.czcxy.xj.basicserver.goods.model;

import cn.czcxy.xj.core.util.tree.BaseTree;

import java.util.List;

public class GoodsTypeTree extends GoodsType implements BaseTree<GoodsTypeTree, String> {

    private List<GoodsTypeTree> children;

    private boolean hasChild;

    @Override
    public String getPid() {
        return super.getParentId();
    }

    @Override
    public void setChildren(List<GoodsTypeTree> e) {
        this.children = e;
    }

    @Override
    public List<GoodsTypeTree> getChildren() {
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
