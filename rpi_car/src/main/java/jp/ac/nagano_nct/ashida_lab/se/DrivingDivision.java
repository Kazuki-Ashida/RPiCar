package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 駆動部
 * @author ashida
 */
public class DrivingDivision extends Thread{

	/** 最低速度 */
	private static final int _MIN_SPEED = 0;
	/** 最高速度 */
	private static final int _MAX_SPEED = 100;

	/**
	 * シングルトンのインスタンス
	 */
	private static DrivingDivision _instance = new DrivingDivision();

	/** 右側モータ */
	private RightMotor _rightMotor;

	/** 左側モータ */
	private LeftMotor _leftMotor;

	/** 右側のロータリエンコーダ */
	private RightRotaryEncoder _rightRotaryEncoder;

	/** 左側のロータリエンコーダ */
	private LeftRotaryEncoder _leftRotaryEncoder;

	/** 動作中か */
	private volatile boolean _isRunning = false;

	/** 適切な左右の回転比．(右のモータの回転数) ÷ (左のモータの回転数) */
	private double _suitableRatio = 1.0F;

	/**
	 * コンストラクタ
	 */
	private DrivingDivision() {
		_rightMotor = RightMotor.getInstance();
		_leftMotor = LeftMotor.getInstance();
		_rightRotaryEncoder = RightRotaryEncoder.getInstance();
		_leftRotaryEncoder = LeftRotaryEncoder.getInstance();
		this.start();
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static DrivingDivision getInstance() {
		return _instance;
	}

	/**
	 * 直進する
	 * @param speed 速度(0～100)
	 * @throws InvalidSpeedException 不正なスピードが設定されたときに発生する例外
	 */
	public void goStraight(int speed) throws InvalidSpeedException{
		if(_MIN_SPEED>speed || _MAX_SPEED<speed){
			throw new InvalidSpeedException(speed);
		}
		_rightMotor.rotate(speed,RotateDirection.CLOCKWISE);
		_leftMotor.rotate(speed, RotateDirection.COUNTER_CLOCKWISE);
		_isRunning = true;
	}

	/**
	 * 後退する
	 * @param speed 速度(0～100)
	 * @throws InvalidSpeedException 不正なスピードが設定されたときに発生する例外
	 */
	public void goBack(int speed) throws InvalidSpeedException{
		if(_MIN_SPEED>speed || _MAX_SPEED<speed){
			throw new InvalidSpeedException(speed);
		}
		_rightMotor.rotate(speed,RotateDirection.COUNTER_CLOCKWISE);
		_leftMotor.rotate(speed, RotateDirection.CLOCKWISE);
		_isRunning = true;
	}

	/**
	 * 右旋回する
	 * @param speed 速度(0～100)
	 * @throws InvalidSpeedException 不正なスピードが設定されたときに発生する例外
	 */
	public void rotateRight(int speed) {
		if(_MIN_SPEED>speed || _MAX_SPEED<speed){
			throw new InvalidSpeedException(speed);
		}
		_rightMotor.rotate(speed,RotateDirection.COUNTER_CLOCKWISE);
		_leftMotor.rotate(speed, RotateDirection.COUNTER_CLOCKWISE);
		_isRunning = true;

	}

	/**
	 * 左旋回する
	 * @param speed 速度(0～100)
	 * @throws InvalidSpeedException 不正なスピードが設定されたときに発生する例外
	 */
	public void rotateLeft(int speed) {
		if(_MIN_SPEED>speed || _MAX_SPEED<speed){
			throw new InvalidSpeedException(speed);
		}
		_rightMotor.rotate(speed,RotateDirection.CLOCKWISE);
		_leftMotor.rotate(speed, RotateDirection.CLOCKWISE);
		_isRunning = true;
	}

	/**
	 * 停止する
	 */
	public void stopMotion() {
		_isRunning = false;
	}

	/** 曲がる
	 * @param right_speed 右側のタイヤの速度．-100～100の値をとる．マイナスの時には後退，プラスの時には前進するような回転をする．
	 * @param left_speed 左側のタイヤの速度．-100～100の値をとる．マイナスの時には後退，プラスの時には前進するような回転をする．
	 * @throws InvalidSpeedException 不正なスピードが設定されたときに発生する例外
	 */
	public void steer(int right_speed, int left_speed){
		if(-_MAX_SPEED>right_speed || _MAX_SPEED<right_speed){
			throw new InvalidSpeedException(right_speed);
		}
		if(-_MAX_SPEED>left_speed || _MAX_SPEED<left_speed){
			throw new InvalidSpeedException(left_speed);
		}

		if(right_speed > 0) {
			_rightMotor.rotate(right_speed, RotateDirection.CLOCKWISE);
		}
		else{
			_rightMotor.rotate(right_speed, RotateDirection.COUNTER_CLOCKWISE);
		}
		if(left_speed > 0) {
			_leftMotor.rotate(left_speed, RotateDirection.CLOCKWISE);
		}
		else{
			_leftMotor.rotate(left_speed, RotateDirection.COUNTER_CLOCKWISE);
		}
		_suitableRatio = Math.abs(right_speed)/Math.abs(left_speed);
		_isRunning = true;
	}

	@Override
	public void run(){
		while(true) {
			/* 動作中の場合 */
			while (_isRunning) {
				/* 現在の回転比を得る */
				double real_ratio = (double)_rightRotaryEncoder.getRotationNum()/_leftRotaryEncoder.getRotationNum();
				/* 適切な回転比と比べ，実際の回転比の方が大きい = 右側のモータの方が回りすぎている場合*/
				if(real_ratio > _suitableRatio){
					/* 右側のモータの速度を遅くする */
					_rightMotor.setCoefficient(1/real_ratio);
					_leftMotor.setCoefficient(1.0F);
				}
				/* 適切な回転比と比べ，実際の回転比の方が小さい = 左側のモータの方が回りすぎている場合*/
				else if(real_ratio < _suitableRatio){
					/* 左側のモータの速度を遅くする */
					_leftMotor.setCoefficient(real_ratio);
					_rightMotor.setCoefficient(1.0F);
				}
			}
		}
	}


}
