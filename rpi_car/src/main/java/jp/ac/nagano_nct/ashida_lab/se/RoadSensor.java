package jp.ac.nagano_nct.ashida_lab.se;

import java.util.HashMap;

/**
 * 道路センサ
 * @author ashida
 */
public class RoadSensor {

	/**
	 * センサの検知状況．白→false，黒→true
	 */
	private HashMap<PhotoReflectorLocation, Boolean> _sensingStatus = new HashMap<>();

	/** シングルトンのインスタンス */
	private static RoadSensor _instance = new RoadSensor();

	/**
	 * リスナー
	 */
	private RoadSensorListener _listener;

	/**
	 * 真ん中のフォトリフレクタ
	 */
	private CenterPhotoReflector _centerPhotoReflector;

	/**
	 * 左側のフォトリフレクタ
	 */
	private LeftPhotoReflector _leftPhotoReflector;

	/**
	 * 右側のフォトリフレクタ
	 */
	private RightPhotoReflector _rightPhotoReflector;

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static RoadSensor getInstance() {
		return _instance;
	}

	/**
	 * デフォルトコンストラクタ
	 */
	private RoadSensor() {
		_leftPhotoReflector = LeftPhotoReflector.getInstance();
		_centerPhotoReflector = CenterPhotoReflector.getInstance();
		_rightPhotoReflector = RightPhotoReflector.getInstance();

		_sensingStatus.put(PhotoReflectorLocation.LEFT, _leftPhotoReflector.isBlack());
		_sensingStatus.put(PhotoReflectorLocation.CENTER, _centerPhotoReflector.isBlack());
		_sensingStatus.put(PhotoReflectorLocation.RIGHT, _rightPhotoReflector.isBlack());

		_leftPhotoReflector.setListener(PhotoReflectorListener -> {
			_sensingStatus.put(PhotoReflectorLocation.LEFT, _leftPhotoReflector.isBlack());
			_listener.handleRoadSensorStateChangeEvent(_sensingStatus);
		});

		_centerPhotoReflector.setListener(PhotoReflectorListener -> {
			_sensingStatus.put(PhotoReflectorLocation.CENTER, _centerPhotoReflector.isBlack());
			_listener.handleRoadSensorStateChangeEvent(_sensingStatus);
		});

		_rightPhotoReflector.setListener(PhotoReflectorListener -> {
			_sensingStatus.put(PhotoReflectorLocation.RIGHT, _rightPhotoReflector.isBlack());
			_listener.handleRoadSensorStateChangeEvent(_sensingStatus);
		});

	}

	/**
	 * 道路センサが変化したことを知らせるリスナをセットする
	 * @param listener リスナ
	 */
	public void setListener(RoadSensorListener listener) {
		_listener = listener;
	}

}
