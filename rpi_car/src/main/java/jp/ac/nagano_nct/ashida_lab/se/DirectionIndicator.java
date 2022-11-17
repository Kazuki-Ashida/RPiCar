package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 方向指示器
 * @author ashida
 */
public abstract class DirectionIndicator extends Light implements AutoCloseable{

	/**
	 * 点滅の間隔[ms]
	 */
	private static final int _BLINKING_INTERVAL = 250;

	/** 点滅しているか */
	private boolean _isBlinking = false;

	/** 終了したか */
	private boolean _isDone = false;

	/** コンストラクタ
	 * @param pin_num 端子番号
	 */
	protected DirectionIndicator(int pin_num){
		super(pin_num);
		Thread t = new Thread(){
			@Override
			public void run(){
				while(!_isDone){
					try{
						if(_isBlinking){
							turnOn();
							Thread.sleep(_BLINKING_INTERVAL);
							turnOff();
							Thread.sleep(_BLINKING_INTERVAL);
						}
					}catch(Exception e){
						
					}
				}
			}
		};
		t.start();
	}
	/**
	 * 点滅開始
	 */
	public void startBlinking() {
		_isBlinking = true;
	}

	/**
	 * 点滅停止
	 */
	public void stopBlinking() {
		_isBlinking = false;
		turnOff();
	}

	@Override
	public void close() throws Exception{
		_isDone = true;
	}
}
