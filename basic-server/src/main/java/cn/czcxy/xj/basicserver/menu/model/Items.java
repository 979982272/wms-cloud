package cn.czcxy.xj.basicserver.menu.model;


import lombok.*;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    private String text;
    private String url;
    private List<Items> items;
    private boolean encoded;

}
