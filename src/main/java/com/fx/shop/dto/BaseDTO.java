package com.fx.shop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 基类，所有dto都要继承它
 *
 * @author: John
 * @create: 2019-05-22 11:47
 * @desc:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

}
