package lihu.zhuanlemei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import lihu.zhuanlemei.util.FileUploader;
import lihu.zhuanlemei.util.FileUploader.UploadInfo;
import lihu.zhuanlemei.util.ImageUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片操作
 */
@Controller
@RequestMapping("/image")
public class ImageController {
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Resource
	FileUploader fileUploder;

	@RequestMapping("/upload")
	@ResponseBody
	Map<String, Object> upload(@RequestParam(required = false) MultipartFile img) throws Exception {
		logger.info("上传图片");

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "error");
		result.put("message", "未知错误");

		if (img == null) {
			throw new Exception("未上传文件");
		}

		Map<String, Object> imageSize = ImageUtil.getImageSize(img.getInputStream());
		result.putAll(imageSize);

		String url = fileUploder.uploadImage(img);
		result.put("url", url);

		result.put("status", "success");
		return result;

	}

	@RequestMapping("/crop")
	@ResponseBody
	Map<String, Object> crop(String imgUrl, Double imgW, Double imgH, Double imgX1, Double imgY1, Double cropW,
			Double cropH) throws Exception {
		logger.info("截取图片 imgUrl={}", imgUrl);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "error");
		result.put("message", "未知错误");

		if (StringUtils.isEmpty(imgUrl) || imgW == null || imgH == null || imgX1 == null || imgY1 == null
				|| cropW == null || cropH == null) {
			throw new Exception("输入参数不正确");
		}

		UploadInfo uploadInfo = fileUploder.getUploadInfo(imgUrl);
		String dest = uploadInfo.getThumbnailFilePath();
		String url = uploadInfo.getThumbnailFileUrl();

		String imgPath = fileUploder.getFilePath(imgUrl);
		ImageUtil.crop(imgPath, dest, imgW.intValue(), imgH.intValue(), imgX1.intValue(), imgY1.intValue(),
				cropW.intValue(), cropH.intValue());

		fileUploder.delete(imgUrl);

		result.put("url", url);
		result.put("status", "success");
		return result;

	}
}
