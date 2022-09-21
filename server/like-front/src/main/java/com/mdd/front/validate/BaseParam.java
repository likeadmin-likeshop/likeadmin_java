package com.mdd.front.validate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface create{}
    public interface update{}
    public interface delete{}
    public interface change{}

}
