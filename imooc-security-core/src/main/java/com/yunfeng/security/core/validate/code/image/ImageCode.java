package com.yunfeng.security.core.validate.code.image;

import com.yunfeng.security.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * <p>
 * 图形验证码
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-12
 */
@Data
public class ImageCode extends ValidateCode {


    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, LocalDateTime.now().plusSeconds(expireIn));
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }

}
