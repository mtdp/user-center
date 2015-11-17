package me.wanx.usercenter.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 验证码action
* @ClassName: CaptchaAction 
* @Description: TODO 
* @author gqwang
* @date 2015年8月11日 上午11:32:59 
*
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaAction{
	
	/**
	 * 生成图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getImage.do")
	protected void getCaptchaImage(HttpServletRequest request,HttpServletResponse response,
								   @RequestParam("width")int width,@RequestParam("height")int height){
		//输入类型
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		//过期时间
		response.setDateHeader("Expires", 0);
		
		HttpSession session = request.getSession();
		
		//如果width,height为空,默认width=60px,height=20px
		if(width == 0 || height == 0){
			width = 60;
			height = 20;
		}
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		//画布 长方形
		//g.setColor(getRandColor(100,200));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		//设置线的颜色
		g.setColor(getRandColor(50,100));
		// 生成随机类
		Random random = new Random();
		//划线
		for(int i = 0; i < 30; i++){
			int x1 = random.nextInt(height);
			int y1 = random.nextInt(width);
			int x2 = random.nextInt(30);
			int y2 = random.nextInt(30);
			g.drawLine(x1, y1, height+x2, width+y2);
		}
		//生成随机码
		String words = "23456789abcdefghkmnpqrstxyzABCDEFGHKMNPQRSTXYZ";
		char[] charArr = words.toCharArray();
		StringBuilder randStr = new StringBuilder();
		for(int j = 0; j < 4; j++){
			char ch = charArr[random.nextInt(words.length())];
			//设置字符的颜色
			g.setColor(getRandColor(60, 120));
			g.drawString(String.valueOf(ch), (j*12)+6, height/2+7);
			randStr.append(ch);
		}
		session.setAttribute("captcha", randStr.toString());
		//生成图像
		g.dispose();
		//将图像写到response中
		ServletOutputStream sos = null;
		try {
			 sos = response.getOutputStream();
			 ImageIO.write(image, "JPEG", sos);
			 sos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sos != null){
				try {
					sos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getJson.do")
	public foo resultJson(){
		foo f = new foo("test","test json");
		return f;
	}
	
	@ResponseBody
	@RequestMapping(value="/getStr.do")
	public String resultStr(){
		return "测试中文";
	}
	
	/**
	 * 随机生产color
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	class foo implements Serializable{
		private static final long serialVersionUID = 1L;
		public foo(){}
		public foo(String id,String name){
			this.id=id;
			this.name=name;
		}
		String id;
		String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
