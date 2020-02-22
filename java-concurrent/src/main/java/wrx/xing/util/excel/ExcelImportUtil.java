/*
package wrx.xing.util.excel;

import com.jd.medicine.base.common.annotation.ExcelField;
import com.jd.medicine.base.common.util.DateUtil;
import com.jd.medicine.base.common.util.StringUtil;
import com.jd.medicine.base.common.util.excel.rule.CheckResult;
import com.jd.medicine.base.common.util.excel.rule.RuleHandler;
import com.jd.medicine.base.common.util.excel.rule.impl.NoRuleHandler;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

*/
/**
 * 请填写类的描述
 * excel导入工具，必须配合@ExcelField注解使用
 * @author wangruxing
 * @date 2018-12-07 09:51
 *//*

public class ExcelImportUtil {

	*/
/**
	 * excel2007版
	 *//*

	private static final String EXCEL2007_SUFFIX = "xlsx";
	*/
/**
	 * excel2003版
	 *//*

	private static final String EXCEL2003_SUFFIX = "xls";

	*/
/**
	 * 工作薄对象
	 *//*

	private Workbook wb;

	*/
/**
	 * 工作表对象
	 *//*

	private Sheet sheet;

	*/
/**
	 * 标题行号
	 *//*

	private int headerNum;
	*/
/**
	 * 红色背景
	 *//*

	private CellStyle csRed;
	*/
/**
	 * 白色背景
	 *//*

	private CellStyle csWhite;
	*/
/**
	 * poi工厂
	 *//*

	private CreationHelper factory;
	*/
/**
	 * 签名
	 *//*

	private ClientAnchor anchor;
	*/
/**
	 * 画笔
	 *//*

	private Drawing drawing;

	*/
/**
	 * 工具初始化
	 * @param file 上传的文件
	 * @param sheetNum 读取第几个sheet
	 * @param headerNum 表头号
	 * @throws IOException
	 *//*

	public ExcelImportUtil(MultipartFile file, int sheetNum, int headerNum) throws IOException {
		if (null == file) {
			throw new RuntimeException("文件不能为空");
		}else if (headerNum < 0){
			throw new RuntimeException("标题行编码不能小于0");
		}else if (file.getOriginalFilename().toLowerCase().endsWith(EXCEL2003_SUFFIX)){
			this.wb = new HSSFWorkbook(file.getInputStream());
			// ClientAnchor是附属在WorkSheet上的一个对象，  其固定在一个单元格的左上角和右下角.
			this.anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 5, (short) 5, 10);
		}else if (file.getOriginalFilename().toLowerCase().endsWith(EXCEL2007_SUFFIX)) {
			this.wb = new XSSFWorkbook(file.getInputStream());
			// ClientAnchor是附属在WorkSheet上的一个对象，  其固定在一个单元格的左上角和右下角.
			this.anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 5, (short) 5, 10);
		}else {
			throw new RuntimeException("文件格式错误");
		}
		this.sheet = wb.getSheetAt(sheetNum);
		this.headerNum = headerNum;
		//得到一个换图的对象
		this.drawing = this.sheet.createDrawingPatriarch();

		// 得到一个POI的工具类
		this.factory = wb.getCreationHelper();

		//设置背景色为黄色
		this.csRed = wb.createCellStyle();
		csRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		csRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
		csRed.setBorderBottom(CellStyle.BORDER_THIN);
		csRed.setBorderLeft(CellStyle.BORDER_THIN);
		csRed.setBorderRight(CellStyle.BORDER_THIN);
		csRed.setBorderTop(CellStyle.BORDER_THIN);

		//设置背景色为白色
		this.csWhite = wb.createCellStyle();
		csWhite.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		csWhite.setFillPattern(CellStyle.SOLID_FOREGROUND);
		csWhite.setBorderBottom(CellStyle.BORDER_THIN);
		csWhite.setBorderLeft(CellStyle.BORDER_THIN);
		csWhite.setBorderRight(CellStyle.BORDER_THIN);
		csWhite.setBorderTop(CellStyle.BORDER_THIN);
	}

	*/
/**
	 * 构造方法二，默认读第一个sheet，表头从0开始
	 * @param file 上传的文件
	 * @throws IOException
	 *//*

	public ExcelImportUtil(MultipartFile file) throws IOException {
		this(file,0,0);
	}

	*/
/**
	 * 构造方法三
	 * @param file 上传的文件
	 * @param sheetNum 读取第几个sheet
	 * @throws IOException
	 *//*

	public ExcelImportUtil(MultipartFile file,int sheetNum) throws IOException {
		this(file,sheetNum,0);
	}

	*/
/**
	 * 执行方法
	 * @param cls 需要映射的类信息
	 * @param <T> 类的泛型
	 * @return 返回数据
	 * @throws Exception 可能有数据转换异常或IO异常
	 *//*

	public <T> List<T> execute(Class<T> cls) throws Exception {
		// 拿到所有字段
		Field[] fs = cls.getDeclaredFields();
		// Map<表头名，字段名>
		Map<String,String> fieldMap = new HashMap<>(16);
		for (Field f : fs) {
			// 找到指定注解
			ExcelField ef = f.getAnnotation(ExcelField.class);
			if (null != ef) {
				fieldMap.put(ef.header(),f.getName());
			}
		}
		if (fieldMap.isEmpty()) {
			throw new RuntimeException("未找到@ExcelField注解");
		}
		// 获取表头行
		Row headerRow = sheet.getRow(headerNum);
		Map<Integer,String> ruleMap = new HashMap<>(16);
		// 所有要导出的表头集合
		Set<String> headerNames = fieldMap.keySet();
		for (int i = 0; i < (int) headerRow.getLastCellNum(); i++) {
			String value = getCellValue(headerRow.getCell(i));
			if (headerNames.contains(value)) {
				ruleMap.put(i,fieldMap.get(value));
			}
		}

		// 返回结果
		List<T> list = new ArrayList<>(500);

		// 跳过表头循环
		for (int i = headerNum + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			T t = cls.newInstance();
			Row row = sheet.getRow(i);
			for (Map.Entry<Integer, String> entry : ruleMap.entrySet()) {
				int index = entry.getKey();
				Cell cell = row.getCell(index);
				String value;
				try {
					value = getCellValue(cell);
				} catch (Exception e) {
					throw new RuntimeException("第" + (i + 1) + "行第"+ (index + 1 ) +"列读取数据失败",e);
				}
				if (StringUtil.isNotEmpty(value)) {
					Field field = cls.getDeclaredField(ruleMap.get(index));
					field.setAccessible(true);
					Class z = field.getType();
					if (z == Integer.class) {
						field.set(t, (int) Double.parseDouble(value));
					} else if (z == String.class) {
						field.set(t,value);
					} else if (z == Double.class) {
						field.set(t, Double.parseDouble(value));
					} else if (z == Long.class){
						field.set(t, (long) Double.parseDouble(value));
					}else if (z == Date.class) {
						field.set(t, DateUtil.parseDate(value,"yyyy-MM-dd HH:mm:ss"));
					}else if (z == BigDecimal.class) {
						field.set(t, new BigDecimal(value));
					}else {
						field.set(t,value);
					}
				}
			}
			list.add(t);
		}
		return list;
	}


	*/
/**
	 * 获取单元格中的值
	 * @param cell 单元格
	 * @return 返回数据
	 *//*

	private String getCellValue(Cell cell){
		String value = null;
		if (null != cell) {

			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
				// 处理日期格式
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					if (null != date) {
						value = DateUtil.formatDate(date,"yyyy-MM-dd HH:mm:ss");
					}
				}else {
					value = String.valueOf(cell.getNumericCellValue());
				}
			}else if (cell.getCellType() == Cell.CELL_TYPE_STRING){
				value = cell.getStringCellValue();
			}else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
				value = cell.getCellFormula();
			}else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
				value = cell.getBooleanCellValue() ? "true" : "false";
			}else if (cell.getCellType() == Cell.CELL_TYPE_ERROR){
				value = String.valueOf(cell.getErrorCellValue());
			}
		}

		return value;
	}


	public <T> ImportResult<List<T>> ruleExecute(Class<T> cls) throws Exception{
		ImportResult<List<T>> result = new ImportResult<>();
		result.setSuccess(true);

		// 拿到所有字段
		Field[] fs = cls.getDeclaredFields();
		// Map<表头名，字段名>
		Map<String,String> fieldMap = new HashMap<>(16);
		Map<String,RuleHandler> ruleHeaderMap = new HashMap<>(16);
		Map<String,String> ruleParamMap = new HashMap<>(16);
		for (Field f : fs) {
			// 找到指定注解
			ExcelField ef = f.getAnnotation(ExcelField.class);
			if (null != ef) {
				fieldMap.put(ef.header(),f.getName());
				if (ef.rule() != NoRuleHandler.class) {
					try {
						ruleHeaderMap.put(ef.header(),ef.rule().newInstance());
						ruleParamMap.put(ef.header(),ef.ruleParam());
					} catch (Exception e) {
						throw new RuntimeException(String.format("%s创建实例失败",ef.rule()));
					}
				}
			}
		}
		if (fieldMap.isEmpty()) {
			throw new RuntimeException("未找到@ExcelField注解");
		}
		// 获取表头行
		Row headerRow = sheet.getRow(headerNum);
		Map<Integer,String> ruleMap = new HashMap<>(16);
		Map<Integer,RuleHandler> ruleHandlerMap = new HashMap<>(16);
		Map<Integer,String> ruleParamIndexMap = new HashMap<>(16);
		// 所有要导出的表头集合
		Set<String> headerNames = fieldMap.keySet();
		for (int i = 0; i < (int) headerRow.getLastCellNum(); i++) {
			String value = getCellValue(headerRow.getCell(i));
			if (headerNames.contains(value)) {
				ruleMap.put(i,fieldMap.get(value));
				if (ruleHeaderMap.get(value) != null) {
					ruleHandlerMap.put(i,ruleHeaderMap.get(value));
					ruleParamIndexMap.put(i,ruleParamMap.get(value));
				}
			}
		}

		// 返回结果
		List<T> list = new ArrayList<>(500);

		// 跳过表头循环
		for (int i = headerNum + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			T t = cls.newInstance();
			Row row = sheet.getRow(i);
			for (Map.Entry<Integer, String> entry : ruleMap.entrySet()) {
				int index = entry.getKey();
				Cell cell = row.getCell(index);

				// 取消颜色以及备注
				if (null != cell.getCellComment()) {
					cell.removeCellComment();
					cell.setCellStyle(csWhite);
				}

				String value;
				try {
					value = getCellValue(cell);
				} catch (Exception e) {
					throw new RuntimeException("第" + (i + 1) + "行第"+ (index + 1 ) +"列读取数据失败",e);
				}

				RuleHandler ruleHandler = ruleHandlerMap.get(index);
				if (null != ruleHandler) {
					CheckResult check = ruleHandler.check(value,ruleParamIndexMap.get(index));
					if (!check.isSuccess()) {
						setErrorNote(cell,check.getErrorMsg());
						result.setSuccess(false);
						continue;
					}
				}

				if (StringUtil.isNotEmpty(value) && result.isSuccess()) {
					Field field = cls.getDeclaredField(entry.getValue());
					field.setAccessible(true);
					Class z = field.getType();
					if (z == Integer.class) {
						field.set(t, (int) Double.parseDouble(value));
					} else if (z == String.class) {
						field.set(t,value);
					} else if (z == Double.class) {
						field.set(t, Double.parseDouble(value));
					} else if (z == Long.class){
						field.set(t, (long) Double.parseDouble(value));
					}else if (z == Date.class) {
						field.set(t, DateUtil.parseDate(value,"yyyy-MM-dd HH:mm:ss"));
					}else if (z == BigDecimal.class) {
						field.set(t, new BigDecimal(value));
					}else {
						field.set(t,value);
					}
				}
			}
			list.add(t);
		}

		if (result.isSuccess()) {
			result.setT(list);
		}
		result.setWb(wb);

		return result;
	}

	private void setErrorNote(Cell cell, String errorMsg) {
		cell.setCellStyle(csRed);
		// 对这个单元格加上注解
		Comment comment0 = this.drawing.createCellComment(this.anchor);
		comment0.setAuthor("Admin");
		RichTextString str0 = this.factory.createRichTextString(errorMsg);
		comment0.setString(str0);
		cell.setCellComment(comment0);
	}

}
*/
