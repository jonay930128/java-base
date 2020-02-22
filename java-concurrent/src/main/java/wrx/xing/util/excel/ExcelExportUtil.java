/*
package wrx.xing.util.excel;

import com.jd.medicine.base.common.annotation.ExcelField;
import com.jd.medicine.base.common.util.CollectionUtil;
import com.jd.medicine.base.common.util.DateUtil;
import com.jd.medicine.base.common.util.StringUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

*/
/**
 * 请填写类的描述
 * excel导出工具
 * @author wangruxing
 * @date 2018-12-07 12:46
 *//*

public class ExcelExportUtil {

	*/
/**
	 * 导出数据
	 * @param fileName 文件名
	 * @param list 数据
	 * @param response 响应
	 * @param <E> 泛型
	 * @throws Exception
	 *//*

	public static <E> void export(String fileName, List<E> list, HttpServletResponse response) throws Exception {
		if (StringUtil.isEmpty(fileName)) {
			throw new RuntimeException("文件名不能为空");
		}
		if (CollectionUtil.isEmpty(list)) {
			throw new RuntimeException("数据不能为空");
		}

		// LinkedHashMap<字段名,表头名>，键值对已经排好顺序
		LinkedHashMap<String, String> fieldAndHeadMap = orderedMap(list.get(0).getClass());

		Workbook wb = createExcel(list,fieldAndHeadMap);
		write(response,fileName,wb);

	}

	*/
/**
	 * 导出excel
	 * @param list 数据
	 * @param <E> 泛型
	 * @return 返回excel
	 * @throws Exception 未知异常
	 *//*

	public static <E> Workbook export(List<E> list) throws Exception {
		if (CollectionUtil.isEmpty(list)) {
			throw new RuntimeException("数据不能为空");
		}

		// LinkedHashMap<字段名,表头名>，键值对已经排好顺序
		LinkedHashMap<String, String> fieldAndHeadMap = orderedMap(list.get(0).getClass());

		return createExcel(list,fieldAndHeadMap);
	}

	*/
/**
	 * 导出模板
	 * @param fileName 文件名
	 * @param cls 类型
	 * @param response 响应
	 * @throws Exception
	 *//*

	public static void exportTemplate(String fileName, Class cls, HttpServletResponse response) throws Exception {
		if (StringUtil.isEmpty(fileName)) {
			throw new RuntimeException("文件名不能为空");
		}

		Workbook wb = createExcel(getHeaderNames(cls));
		write(response,fileName,wb);
	}


	*/
/**
	 * 返回有顺序的map,LinkedHashMap<字段名,表头名>
	 * @param cls 类型
	 * @return 返回
	 *//*

	private static LinkedHashMap<String,String> orderedMap(Class cls){
		// 拿到所有字段
		Field[] fs = cls.getDeclaredFields();
		// Map<字段名,排序分数> 不用分数作为key是因为分数有重复，value会覆盖
		Map<String,Integer> fieldMap = new HashMap<>(16);
		// Map<列名,排序分数> 不用分数作为key是因为分数有重复，value会覆盖
		Map<String,Integer> headerNameMap = new HashMap<>(16);
		for (Field f : fs) {
			// 找到指定注解
			ExcelField ef = f.getAnnotation(ExcelField.class);
			if (null != ef) {
				fieldMap.put(f.getName(),ef.sort());
				headerNameMap.put(ef.header(),ef.sort());
			}
		}
		if (fieldMap.isEmpty()) {
			throw new RuntimeException("未找到@ExcelField注解");
		}

		// 字段集合
		List<String> fieldList = sortByValue(fieldMap);
		// 表头集合
		List<String> headerNameList = sortByValue(headerNameMap);
		// 返回,用LinkedHashMap主要是因为他按插入顺序存储
		LinkedHashMap<String,String> map = new LinkedHashMap<>(fieldMap.size());

		for (int i = 0; i < fieldList.size(); i++) {
			map.put(fieldList.get(i),headerNameList.get(i));
		}

		return map;
	}

	*/
/**
	 * 获取表头集合
	 * @param cls 类型
	 * @return 返回集合
	 *//*

	private static List<String> getHeaderNames(Class cls){
		// 拿到所有字段
		Field[] fs = cls.getDeclaredFields();
		// Map<列名,排序分数> 不用分数作为key是因为分数有重复，value会覆盖
		Map<String,Integer> headerNameMap = new HashMap<>(16);
		for (Field f : fs) {
			// 找到指定注解
			ExcelField ef = f.getAnnotation(ExcelField.class);
			if (null != ef) {
				headerNameMap.put(ef.header(),ef.sort());
			}
		}
		if (headerNameMap.isEmpty()) {
			throw new RuntimeException("未找到@ExcelField注解");
		}

		// 字段集合
		return sortByValue(headerNameMap);
	}


	*/
/**
	 * 返回按map中的value值排序的key集合
	 * @param map map
	 * @return 返回集合
	 *//*

	private static List<String> sortByValue(Map<String,Integer> map){
		// 对字段排序
		List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
			// 升序
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return Integer.compare(o1.getValue(),o2.getValue());
			}
		});
		// 排好序的字段集合
		List<String> keyList = new ArrayList<>(entryList.size());
		for (Map.Entry<String, Integer> entry : entryList) {
			keyList.add(entry.getKey());
		}
		return keyList;
	}



	*/
/**
	 * 创建excel
	 * @param list 数据
	 * @param fieldAndHeadMap <字段名，表头名>
	 * @param <E> 类型
	 * @return 返回工作簿
	 * @throws Exception
	 *//*

	private static <E> Workbook createExcel(List<E> list, LinkedHashMap<String, String> fieldAndHeadMap) throws Exception {
		Workbook wb = new SXSSFWorkbook(500);
		Sheet sheet = wb.createSheet("export");
		sheet.setDefaultColumnWidth(20);
		// 设置居中
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 创建表头
		createHeader(sheet,fieldAndHeadMap.values(),cellStyle);

		Class<?> cls = list.get(0).getClass();
		// 写数据
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(i + 1);
			createCell(row,list.get(i),fieldAndHeadMap.keySet(),cls,cellStyle);
		}

		return wb;
	}

	*/
/**
	 * 创建模板
	 * @param headerNames 表头数据集合
	 * @return 返回工作簿
	 *//*

	private static Workbook createExcel(List<String> headerNames) {
		Workbook wb = new SXSSFWorkbook(500);
		Sheet sheet = wb.createSheet("template");
		sheet.setDefaultColumnWidth(20);
		// 设置居中
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 创建表头
		createHeader(sheet,headerNames,cellStyle);
		return wb;
	}

	*/
/**
	 * 写值
	 * @param <E> 泛型数据
	 * @param row 行
	 * @param e 数据
	 * @param fieldSet 字段集合
	 * @param cls 类型
	 * @param cellStyle 单元格样式
	 * @throws Exception
	 *//*

	private static <E> void createCell(Row row, E e, Set<String> fieldSet, Class<?> cls, CellStyle cellStyle) throws Exception {
		Iterator<String> iterator = fieldSet.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			Field field = cls.getDeclaredField(iterator.next());
			field.setAccessible(true);
			Object obj = field.get(e);
			Class z = field.getType();
			String cellValue;
			if (z == Boolean.class) {
				boolean bool = (boolean) obj;
				cellValue = bool ? "true" : "false";
			}else if (z == Date.class) {
				cellValue = DateUtil.formatDate((Date)obj,"yyyy-MM-dd HH:mm:ss");
			}else {
				cellValue = obj.toString();
			}
			Cell cell = row.createCell(index);
			cell.setCellStyle(cellStyle);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(cellValue);
			index++;
		}
	}

	*/
/**
	 * 创建表头
	 * @param sheet sheet
	 * @param headerNames 集合
	 * @param cellStyle 单元格样式
	 *//*

	private static void createHeader(Sheet sheet, Collection<String> headerNames, CellStyle cellStyle) {
		Row row = sheet.createRow(0);
		Iterator<String> iterator = headerNames.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			String header = iterator.next();
			if (StringUtil.isNotEmpty(header)) {
				Cell cell = row.createCell(index);
				cell.setCellStyle(cellStyle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(header);
			}
			index++;
		}
	}



	*/
/**
	 * 输出到客户端
	 * @param response 响应
	 * @param fileName 输出文件名
	 *//*

	private static void write(HttpServletResponse response, String fileName,Workbook wb) throws IOException {
		fileName += ".xlsx";
		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		wb.write(response.getOutputStream());
	}

}
*/
