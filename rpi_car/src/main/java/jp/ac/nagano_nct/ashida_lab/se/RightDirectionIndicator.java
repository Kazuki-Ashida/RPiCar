package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 右方向指示器
 * @author ashida
 */
public class RightDirectionIndicator extends DirectionIndicator {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 8;

	/**
	 * シングルトンのインスタンス
	 */
	private static RightDirectionIndicator _instance = new RightDirectionIndicator(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private RightDirectionIndicator(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static RightDirectionIndicator getInstance() throws Exception{
		return _instance;
	}

}
