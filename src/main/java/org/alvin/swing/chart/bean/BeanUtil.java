package org.alvin.swing.chart.bean;


import org.alvin.swing.chart.bean.ProjectBean;
import org.alvin.swing.chart.bean.SLABean;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * �������ݴ洢�Ĺ�����
 * 
 * @author Administrator
 * 
 */
public class BeanUtil {

	private BeanUtil() {
	}

	public static ProjectBean currentProject;
	public static ScenarioBean currentScen;
	public static SLABean currentSLABean;

	// public final static String executant_cmd = IOUtil.createExecutant();

	public static Object getObjectFromXML(File dataFile) {
		Object obj = null;
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(dataFile)));
			obj = decoder.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (decoder != null) {
				decoder.close();
			}
		}
		return obj;
	}

	public static void saveObjectToXML(Object obj, File dataFile) throws IOException {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(dataFile)));
			encoder.writeObject(obj);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			if (encoder != null) {
				encoder.flush();
				encoder.close();
			}
		}
	}

	public static Object cloneObject(Object obj) {
		File f = new File(System.nanoTime() + "");
		try {
			saveObjectToXML(obj, f);
			return getObjectFromXML(f);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.gc();
			if (f.delete()) {
				f.deleteOnExit();
			}
		}
		return null;
	}

	public static void copyCollection(Collection<Object> source, Collection<Object> target) {
		Iterator<Object> it = source.iterator();
		while (it.hasNext()) {
			target.add(it.next());
		}
	}

	/**
	 * ���ݴ�����¼������ ʱ���֣���
	 * 
	 * @param time
	 *            ʱ�� һ��Ϊ��λ
	 * @return
	 */
	public static String timeToH(long time) {
		String timeStr = "";
		long m = time / 60;
		long h = m / 60;
		long s = time % 60;
		m = m % 60;

		if (h < 10) {
			timeStr += "0";
		}
		timeStr += h;
		timeStr += ":";
		if (m < 10) {
			timeStr += "0";
		}
		timeStr += m;
		timeStr += ":";
		if (s < 10) {
			timeStr += "0";
		}
		timeStr += s;
		return timeStr;
	}

	/**
	 * ����ʱ������ ��:ʱ����
	 * 
	 * @param time
	 *            ʱ�� һ��Ϊ��λ
	 * @return
	 */
	public static String timeToD(long time) {
		long m = time / 60;
		long h = m / 60;
		long d = h / 24;
		String timeStr = "";

		m = m % 60;
		h = h % 24;

		if (d < 10) {
			timeStr += "0";
		}
		timeStr += d;
		timeStr += ":";
		if (h < 10) {
			timeStr += "0";
		}
		timeStr += h;
		timeStr += ":";
		if (m < 10) {
			timeStr += "0";
		}
		timeStr += m;
		return timeStr;
	}

}
