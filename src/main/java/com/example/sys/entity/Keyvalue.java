package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keyvalue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("keyname")
    private String keyname;

    private String keyvalue;


}
