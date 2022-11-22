package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 不正なディレクトリ名が指定された時の例外
 * @author 芦田
 */
public class InvalidDirectoryNameException extends RuntimeException {

	/**
	 * コンストラクタ
	 * @param invalid_directory_name 不正なディレクトリ名
	 */
	public InvalidDirectoryNameException(String invalid_directory_name) {

	}

	/**
	 * 不正なディレクトリ名を得る
	 * @return 不正なディレクトリ名
	 */
	public String getInvalidDirectoryName() {
		return null;
	}

}
