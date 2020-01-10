package cn.czcxy.xj.core.util.tree;


import java.io.Serializable;
import java.util.List;

/**
 * The class Base tree.
 *
 * @param <E>  the type parameter
 * @param <ID> the type parameter
 *
 * @author zh.
 */
public interface BaseTree<E, ID> extends Serializable {

	ID getId();

	ID getPid();

	void setChildren(List<E> e);

	List<E> getChildren();

	void setHasChild(boolean hasChild);

	boolean getHasChild();
}