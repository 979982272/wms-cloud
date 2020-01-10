package cn.czcxy.xj.basicserver.menu.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单结点
 *
 * @param <k>
 * @param <v>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Node<k, v> {
    // 链表结构 不进行序列化处理
    private v text;
    private v url;
    private k id;
    private boolean encoded = false;
    private List<Node<k, v>> items;
    // 选中
    private String checked;
    // 展开
    private boolean expanded = false;
}

