package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.io.i2c.I2C;

public class EndianReaders {

	/**
	 * Read an unsigned byte from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param reg register
	 * @param verbose whether verbose mode or not
	 * @return unsigned integer of 8-bits data
	 */
	public static int readU8(I2C device, int i2caddr, int reg, boolean verbose) {
		int result = 0;

		result = device.readRegister(reg);
		if (verbose) {
			System.out.println("I2C: Device " + i2caddr + " (0x" + Integer.toHexString(i2caddr) +
					") returned " + result + " (0x" + Integer.toHexString(result) +
					") from reg " + reg + " (0x" + Integer.toHexString(reg) + ")");
		}

		return result; // & 0xFF;
	}

	/**
	 * Read a signed byte from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param reg register
	 * @param verbose whether verbose mode or not
	 * @return signed integer of 8-bits data
	 */
	public static int readS8(I2C device, int i2caddr, int reg, boolean verbose) {
		int result = 0;

		result = device.readRegister(reg); // & 0x7F;
		if (result > 127)
			result -= 256;
		if (verbose) {
			System.out.println("I2C: Device " + i2caddr + " returned " + result + " from reg " + reg);
		}
		return result; // & 0xFF;
	}

	/**
	 * Read 16 bits unsigned integer (little endian) from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param register register
	 * @param verbose whether verbose mode or not
	 * @return 16 bits unsigned integer that is little endian
	 */
	public static int readU16LE(I2C device, int i2caddr, int register, boolean verbose) {
		return EndianReaders.readU16(device, i2caddr, register, Endianness.LITTLE_ENDIAN, verbose);
	}

	/** Read 16 bits unsigned integer (big endian) from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param register register
	 * @param verbose whether verbose mode or not
	 * @return 16 bits unsigned integer that is big endian
	 */
	public static int readU16BE(I2C device, int i2caddr, int register, boolean verbose) {
		return EndianReaders.readU16(device, i2caddr, register, Endianness.BIG_ENDIAN, verbose);
	}

	/** Read 16 bits unsigned integer with endiannness from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param register register
	 * @param endianness endianness
	 * @param verbose whether verbose mode or not
	 * @return 16 bits unsigned integer
	 */
	public static int readU16(I2C device, int i2caddr, int register, EndianReaders.Endianness endianness, boolean verbose) {
		int hi = EndianReaders.readU8(device, i2caddr, register, verbose);
		int lo = EndianReaders.readU8(device, i2caddr, register + 1, verbose);
		return ((endianness == Endianness.BIG_ENDIAN) ? (hi << 8) + lo : (lo << 8) + hi); // & 0xFFFF;
	}

	/** Read 16 bits signed integer with endiannness from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param register register
	 * @param endianness endianness
	 * @param verbose whether verbose mode or not
	 * @return 16 bits signed integer
	 */
	public static int readS16(I2C device, int i2caddr, int register, EndianReaders.Endianness endianness, boolean verbose) {
		int hi = 0, lo = 0;
		if (endianness == Endianness.BIG_ENDIAN) {
			hi = EndianReaders.readS8(device, i2caddr, register, verbose);
			lo = EndianReaders.readU8(device, i2caddr, register + 1, verbose);
		} else {
			lo = EndianReaders.readU8(device, i2caddr, register, verbose);
			hi = EndianReaders.readS8(device, i2caddr, register + 1, verbose);
		}
		return ((hi << 8) + lo); // & 0xFFFF;
	}

	/**
	 * Read 16 bits signed integer (little endian) from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param register register
	 * @param verbose whether verbose mode or not
	 * @return 16 bits signed integer that is little endian
	 */
	public static int readS16LE(I2C device, int i2caddr, int register, boolean verbose) {
		return EndianReaders.readS16(device, i2caddr, register, Endianness.LITTLE_ENDIAN, verbose);
	}

	/**
	 * Read 16 bits signed integer (big endian) from the I2C device
	 * @param device I2C device
	 * @param i2caddr address
	 * @param register register
	 * @param verbose whether verbose mode or not
	 * @return 16 bits signed integer that is big endian
	 */
	public static int readS16BE(I2C device, int i2caddr, int register, boolean verbose) {
		return EndianReaders.readS16(device, i2caddr, register, Endianness.BIG_ENDIAN, verbose);
	}

	/** Endianness */
	public enum Endianness {
		/** Little endian */
		LITTLE_ENDIAN,
		/** Big endian */
		BIG_ENDIAN
	}

}
