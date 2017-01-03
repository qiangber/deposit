package com.deposit.utils;
import java.awt.Color;   
import java.awt.Font;   
import java.awt.Graphics2D;   
import java.awt.image.BufferedImage;   
import java.util.Random;    
import javax.imageio.ImageIO;   
import javax.servlet.ServletException;   
import javax.servlet.ServletOutputStream;   
import javax.servlet.http.HttpServlet;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import javax.servlet.http.HttpSession;   
  
public class VerifyCodeAction extends HttpServlet {   
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 7931351500456890689L;
	/**
    * 验证码图片的宽度
    */
	
    private int width = 120;   
    /**
    *  验证码图片的高度
    */
    private int height = 40;   
    
    /**
    * 验证码字符个数 
    */
    private int codeCount = 4;   
  
    /**
    * xx
    */
    private int xx = 0;   
  
    /**
    * 字体高度   
    */
    private int fontHeight;   
  
    /**
    * codeY
    */
    private int codeY;   
  
    /**
    * codeSequence
    */
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',   
            'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',   
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };   
  
    /**  
     * 初始化验证图片属性  
     */  
    public void init() throws ServletException {    
        String strWidth = this.getInitParameter("width");   
        String strHeight = this.getInitParameter("height");    
        String strCodeCount = this.getInitParameter("codeCount");   
  
        // 将配置的信息转换成数值   
        try {   
            if (strWidth != null && strWidth.length() != 0) {   
                width = Integer.parseInt(strWidth);   
            }   
            if (strHeight != null && strHeight.length() != 0) {   
                height = Integer.parseInt(strHeight);   
            }   
            if (strCodeCount != null && strCodeCount.length() != 0) {   
                codeCount = Integer.parseInt(strCodeCount);   
            }   
        } catch (NumberFormatException e) {
         e.printStackTrace();
        }   
  
        xx = width / (codeCount );   
        fontHeight = height - 2;   
        codeY = height - 4;   
  
    }   
    protected void service(HttpServletRequest req, HttpServletResponse resp)   
            throws ServletException, java.io.IOException {   
    
        BufferedImage buffImg = new BufferedImage(width, height,   
                BufferedImage.TYPE_INT_RGB);   
        Graphics2D gd = buffImg.createGraphics();   
        Random random = new Random();  
        gd.setColor(Color.WHITE);   
        gd.fillRect(0, 0, width, height);     
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);   
        // 设置字体。   
        gd.setFont(font);   
  
        // 画边框。   
        gd.setColor(Color.BLACK);   
        gd.drawRect(0, 0, width - 1, height - 1);   
  
        // 随机产生100条干扰线
        gd.setColor(Color.BLACK);   
        for (int i = 0; i < 100; i++) {   
            int x = random.nextInt(width);   
            int y = random.nextInt(height);   
            int xl = random.nextInt(12);   
            int yl = random.nextInt(12);   
            gd.drawLine(x, y, x + xl, y + yl);
        }   
   
        StringBuffer randomCode = new StringBuffer();   
        int red = 0, green = 0, blue = 0;  
        for (int i = 0; i < codeCount; i++) {      
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);     
            red = random.nextInt(255);   
            green = random.nextInt(255);   
            blue = random.nextInt(255);      
            gd.setColor(new Color(red, green, blue));   
            gd.drawString(strRand, i * xx, codeY);     
            randomCode.append(strRand);   
        }     
        HttpSession session = req.getSession();   
        session.setAttribute("validateCode", randomCode.toString());      
        resp.setHeader("Pragma", "no-cache");   
        resp.setHeader("Cache-Control", "no-cache");   
        resp.setDateHeader("Expires", 0);   
  
        resp.setContentType("image/jpeg");   
  
        // 将图像输出到Servlet输出流中
        ServletOutputStream sos = resp.getOutputStream();   
        ImageIO.write(buffImg, "jpeg", sos);   
        sos.close();   
    }   
} 