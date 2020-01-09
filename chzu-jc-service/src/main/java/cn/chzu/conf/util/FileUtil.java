package cn.chzu.conf.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.GZIPInputStream;


import cn.chzu.conf.exception.ZycException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class FileUtil 
{
	// 日志
	protected static final Logger log = LoggerFactory.getLogger(FileUtil.class.getName());
	
	/**
	 * classes path
	 */
	public static final String CLASSES_PATHNAME = "/cn/lz/";
	
	private FileUtil()
	{
		
	}
	
	/**
	 * 打开流文件
	 * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
	 * @param fileName 文件名称
	 * @return 输入流
	 */
	public static InputStream getResourceAsStream( String fileName ) throws ZycException
	{
		if( fileName == null || fileName.length() == 0 ){
			throw new ZycException("FILE05", "文件名称为空");
		}
		
		// 取文件名称
		String	resourceName = null;
		if( fileName.startsWith(CLASSES_PATHNAME) ){
			resourceName = fileName;
		}
		else if( fileName.startsWith("/classes") ){
			resourceName = fileName.substring( 8 );
		}
		
		// 打开文件
		InputStream	is = null;
		if( resourceName != null ){
			try{
				is = FileUtil.class.getResourceAsStream( resourceName );
			}
			catch( Exception e ){
				throw new ZycException("FILE01", "文件不存在");
			}
			
			if( is == null ){
				throw new ZycException("FILE01", "文件不存在");
			}
		}
		else{
			// 打开文件系统
			try{
				File fp = new File( fileName );
				if( fp.exists() ){
					is = new FileInputStream( fp );
				}
				else{
					is = FileUtil.class.getResourceAsStream(fileName);
					
					if( is == null ){
						throw new ZycException("FILE01", "文件不存在");
					}
				}
			}
			catch(FileNotFoundException e){
				throw new ZycException("FILE01", "文件不存在");
			}
		}
		
		return is;
	}
	
	/**
	 * 打开流文件
	 * @param file File对象
	 * @return 输入流
	 */
	public static InputStream getResourceAsStream( File file ) throws ZycException
	{
		if( file.exists() == false ){
			throw new ZycException("FILE01", "文件不存在");
		}
		
		// 打开文件系统
		try{
			return new FileInputStream( file );
		}
		catch(FileNotFoundException e){
			throw new ZycException("FILE01", "文件不存在");
		}
	}
	
	/**
	 * 打开文件用于写，清空原文件
	 * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
	 * @param fileName 文件名称
	 * @return 输出流
	 * @throws ZycException
	 */
	public static FileOutputStream openWriteFile( String fileName ) throws ZycException
	{
		// 文件全路径
		fileName = getFullName( fileName );
		if( fileName == null || fileName.length() == 0 ){
			throw new ZycException("FILE05", "文件名称为空");
		}
		
		// 检查并创建文件
		File fp = getFile( fileName );
		
		// 打开文件
		FileOutputStream out;
		try{
			out = new FileOutputStream( fp );
		}
		catch(FileNotFoundException e){
			throw new ZycException("FILE01", "文件不存在");
		}
		
		return out;
	}

	/**
	 * 打开文件用于写，清空原文件
	 * @param file File对象
	 * @return 输出流
	 * @throws ZycException
	 */
	public static FileOutputStream openWriteFile( File file ) throws ZycException
	{
		// 设置写权限
		boolean isExist = file.exists();
		if( isExist && file.canWrite() == false ){
			setWritable( file );
		}
		
		// 判断是否能够打开
		try{
			return new FileOutputStream( file );
		}
		catch(FileNotFoundException e){
			if( isExist ){
				String msg = e.getLocalizedMessage();
				if( msg == null || msg.length() == 0 ){
					throw new ZycException("FILE02", "打开文件时错误");
				}
				else{
					throw new ZycException("FILE02", "打开文件时错误");
				}
			}
		}
		
		// 创建目录
		try{
			if( !isExist ){
				File fp = file.getParentFile();
				if( !fp.exists() ){
					boolean rc = fp.mkdirs();
					if( !rc ){
						throw new ZycException("FILE12", "创建目录时错误");
					}
					
					return new FileOutputStream( file );
				}
			}
		}
		catch(FileNotFoundException ex){
			throw new ZycException("FILE01", "文件不存在");
		}

		throw new ZycException("FILE01", "文件不存在");
	}

   /**
    * 打开文件用于写，在文件的尾部写入内容
    * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
    * @param fileName 文件名称
    * @return 输出流
    * @throws ZycException
    */
   public static FileOutputStream openAppendFile( String fileName ) throws ZycException
   {
	   // 文件全路径
	   fileName = getFullName( fileName );
	   if( fileName == null || fileName.length() == 0 ){
		   throw new ZycException("FILE05", "文件名称为空");
	   }
	   
	   // 检查并创建文件
	   File fp = getFile( fileName );
	   
	   // 打开文件
	   FileOutputStream out;
	   
	   try{
		   out = new FileOutputStream( fp, true );
       }
       catch(FileNotFoundException e){
        throw new ZycException("FILE01", "文件不存在");
       }
	   
	   return out;
   }

   public static FileOutputStream openAppendFile( File file ) throws ZycException
   {
		// 设置写权限
		boolean isExist = file.exists();
		if( isExist && file.canWrite() == false ){
			setWritable( file );
		}
		
		// 判断是否能够打开
		try{
			return new FileOutputStream( file, true );
		}
		catch(FileNotFoundException e){
			if( isExist ){
				String msg = e.getLocalizedMessage();
				if( msg == null || msg.length() == 0 ){
					throw new ZycException("FILE02", "打开文件时错误");
				}
				else{
					throw new ZycException("FILE02", "打开文件时错误");
				}
				
			}
		}
		
		// 创建目录
		try{
			if( !isExist ){
				boolean rc = file.getParentFile().mkdirs();
				if( !rc ){
					throw new ZycException("FILE12", "创建目录时错误");
				}
				
				return new FileOutputStream( file );
			}
		}
		catch(FileNotFoundException ex){
			throw new ZycException("FILE01", "文件不存在");
		}

		throw new ZycException("FILE01", "文件不存在");
   }
   
   /**
    * 保存文件
    * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
    * @param fileName 文件名称
    * @param buffer 写入文件的内容
    * @throws ZycException
    */
   public static void saveFile(String fileName, String buffer) throws ZycException 
   {
	   // 打开文件
	   OutputStream out = openWriteFile( fileName );
	   
	   try{
		   out.write(buffer.getBytes(cn.chzu.conf.util.LzConstants.CHARSET_NAME));
       }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误") ;
       }
       finally{
    	   closeOutputStream( out );
       }
   }

   /**
    * 保存文件
    * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
    * @param fileName 文件名称
    * @param buffer 写入文件的内容
    * @throws ZycException
    */
   public static void saveFile(String fileName, byte[] buffer) throws ZycException 
   {
	   // 打开文件
	   OutputStream out = openWriteFile( fileName );
	   
	   try{
		   out.write( buffer );
       }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误");
       }
       finally{
    	   closeOutputStream( out );
       }
   }

   /**
    * 保存文件
    * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
    * @param fileName 文件名称
    * @param buffer 写入文件的内容
    * @param offset 在buffer中的偏移量
    * @param length 写入文件的内容长度
    * @throws ZycException
    */
   public static void saveFile(String fileName, byte[] buffer, int offset, int length) throws ZycException 
   {
	   // 打开文件
	   OutputStream out = openWriteFile( fileName );
	   
	   try{
		   out.write( buffer, offset, length );
       }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误");
       }
       finally{
    	   closeOutputStream( out );
       }
   }

   /**
    * 保存文件
    * @param file File对象
    * @param buffer 写入文件的内容
    * @throws ZycException
    */
   public static void saveFile(File file, String buffer) throws ZycException 
   {
	   // 打开文件
	   OutputStream out = openWriteFile( file );
	   
	   try{
		   out.write(buffer.getBytes(cn.chzu.conf.util.LzConstants.CHARSET_NAME));
       }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误") ;
       }
       finally{
    	   closeOutputStream( out );
       }
   }

   /**
    * 保存文件
    * @param file File对象
    * @param buffer 写入文件的内容
    * @param charsetName 文件的编码类型
    * @throws ZycException
    */
   public static void saveFile(File file, String buffer, String charsetName) throws ZycException 
   {
	   // 打开文件
	   OutputStreamWriter wt = null;
	   OutputStream out = openWriteFile( file );
	   
	   try{
		   if( charsetName == null ||
				   charsetName.length() == 0 )
		   {
			   out.write(buffer.getBytes(cn.chzu.conf.util.LzConstants.CHARSET_NAME));
		   }
		   else{
			   wt = new OutputStreamWriter(out, charsetName);
			   wt.write( buffer );
		   }
       }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误") ;
       }
       finally{
    	   if( wt != null ){
    		   try{
    			   wt.close();
    		   }
    		   catch( Throwable e ){
    			   log.error( "关闭文件错误" );
    		   }
    	   }
    	   else{
    		   closeOutputStream( out );
    	   }
       }
   }

   /**
    * 保存文件
    * @param file File对象
    * @param buffer 写入文件的内容
    * @throws ZycException
    */
   public static void saveFile(File file, byte[] buffer) throws ZycException 
   {
	   // 打开文件
	   OutputStream out = openWriteFile( file );
	   
	   try{
		   out.write( buffer );
       }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误") ;
       }
       finally{
    	   closeOutputStream( out );
       }
   }

   /**
    * 保存文件
    * @param file File对象
    * @param buffer 写入文件的内容
    * @param offset 在buffer中的偏移量
    * @param length 写入文件的内容长度
    * @throws ZycException
    */
   public static void saveFile(File file, byte[] buffer, int offset, int length) throws ZycException 
   {
	   // 打开文件
	   OutputStream out = openWriteFile( file );
	   
	   try{
		   out.write( buffer, offset, length );
       }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误") ;
       }
       finally{
    	   closeOutputStream( out );
       }
   }

   /**
    * 保存文件
    * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
    * @param fileName 文件名称
    * @param buffer 写入文件的内容
    * @param charsetName 文件编码集
    * @throws ZycException
    */
   public static void saveFile(String fileName, String buffer, String charsetName) throws ZycException 
   {
	   // 打开文件
	   OutputStreamWriter wt = null;
	   OutputStream out = openWriteFile( fileName );
	   
	   try{
		   if( charsetName == null ||
				   charsetName.length() == 0 )
		   {
			   out.write(buffer.getBytes(cn.chzu.conf.util.LzConstants.CHARSET_NAME));
		   }
		   else{
			   wt = new OutputStreamWriter(out, charsetName);
			   wt.write( buffer );
		   }
       }
	   catch(UnsupportedEncodingException e){
		   throw new ZycException("FILE04", "写文件错误");
	   }
       catch(IOException e){
           throw new ZycException("FILE04", "写文件错误");
       }
       finally{
    	   if( wt != null ){
    		   try{
    			   wt.close();
    		   }
    		   catch( Throwable e ){
    			   log.error( "关闭文件错误" );
    		   }
    	   }
    	   else{
    		   closeOutputStream( out );
    	   }
       }
   }

   /**
    * 从文件中读取数据
    * @param fileName 文件名称
    * @return 文件内容
    * @throws ZycException
    */
   public static String readFile(String fileName) throws ZycException 
   {
	   return readFile( fileName, null );
   }
   
   
   /**
    * 读取文件内容
    * @param fileName 文件名称
    * @param charsetName 编码类型
    * @return 文件内容
    * @throws ZycException
    */
   public static String readFile(String fileName, String charsetName) throws ZycException 
   {
	   // 打开文件
	   InputStream	is = getResourceAsStream( fileName );
	   
	   // 读取数据
	   try{
		   return readFile( is, charsetName );
	   }
	   catch( ZycException e ){
		   e.addMessage( "读取文件[" + fileName + "]的内容时错误" );
		   throw e;
	   }
	   finally{
		   try{
			   is.close();
		   }
		   catch( IOException e ){
			   log.warn( "关闭文件时错误:" + fileName );
		   }
	   }
   }

   
   /**
    * 读取文件内容
    * @param file File对象
    * @param charsetName 编码类型
    * @return 文件内容
    * @throws ZycException
    */
   public static String readFile(File file, String charsetName) throws ZycException 
   {
	   // 打开文件
	   InputStream	is = getResourceAsStream( file );
	   
	   // 读取数据
	   try{
		   return readFile( is, charsetName );
	   }
	   catch( ZycException e ){
		   e.addMessage( "读取文件[" + file.getAbsolutePath() + "]的内容时错误" );
		   throw e;
	   }
	   finally{
		   try{
			   is.close();
		   }
		   catch( IOException e ){
			   log.error( "关闭文件时错误:" + file.getAbsolutePath() );
		   }
	   }
   }
   
   /**
    * 读取文件内容
    * @param is 输入流
    * @param charsetName 编码类型
    * @return 文件内容
    * @throws ZycException
    */
   public static String readFile(InputStream is, String charsetName) throws ZycException 
   {
	   // 打开文件
	   BufferedReader in = null;
	   CharArrayWriter out = null;
	   
	   // 读取数据
	   try{
		   if( charsetName != null && charsetName.length() != 0 ){
			   in = new BufferedReader(new InputStreamReader( is, charsetName ));
		   }
		   else{
			   in = new BufferedReader(new InputStreamReader( is, "GBK" ));
			   // in = new BufferedReader(new InputStreamReader(new BufferedInputStream(is)));
		   }
		   
	       int	count;
		   char data[] = new char[2048];
		   out = new CharArrayWriter();
		   while ((count = in.read(data, 0, 2048)) != -1) {
			   out.write(data, 0, count);
		   }

		   return out.toString();
	   }
	   catch( IOException e ){
		   throw new ZycException("FILE03", "读取文件时错误");
	   }
	   finally{
		   if( out != null ){
			   out.close();
		   }
	   }
   }

   /**
    * 读取文件内容
    * @param file File对象
    * @return 文件内容
    * @throws ZycException
    */
   public static byte[] readFile( File file ) throws ZycException
   {
	   InputStream is = getResourceAsStream( file );
	   
	   try{
		   return readFile( is );
	   }
	   finally{
		   closeInputStream( is );
	   }
   }
   
   /**
    * 读取文件内容
    * @param is 输入流
    * @return 文件内容
    * @throws ZycException
    */
   public static byte[] readFile( InputStream is ) throws ZycException
   {
	   ByteArrayOutputStream out = null;
	   
	   try{
	       int	count;
		   byte data[] = new byte[2048];
		   out = new ByteArrayOutputStream();
		   
		   while ((count = is.read(data, 0, 2048)) != -1) {
			   out.write(data, 0, count);
		   }
		   
		   return out.toByteArray();
	   }
	   catch( IOException e ){
		   throw new ZycException("FILE03", "读取文件时错误");
	   }
	   finally{
		   FileUtil.closeOutputStream(out);
	   }
   }

	/**
	 * 读取文件内容
	 * @param is 输入流
	 * @param start 开始读取内容的偏移量
	 * @param length 读取文件的内容长度
	 * @return 文件内容
	 * @throws IOException
	 */
	public static byte[] readFile( InputStream is, long start, long length ) throws IOException
	{
		ByteArrayOutputStream out = null;
		
		try{
			int	count;
			byte data[] = new byte[2048];
			out = new ByteArrayOutputStream();
			
			long rc = is.skip( start );
			if( rc != start ){
				throw new IOException( "移到文件指针时错误:" + rc +"-" + start );
			}
			
			while( length > 0 && (count = is.read(data, 0, 2048)) != -1 ){
				if( count > length ){
					count = (int)length;
					length = 0;
				}
				else{
					length = length - count;
				}
				
				out.write(data, 0, count);
			}
			
			return out.toByteArray();
		}
		finally{
			FileUtil.closeOutputStream( out );
		}
	}

   /**
    * 读取文件内容
    * @param fileName 文件名称
    * @return 文件内容
    * @throws ZycException
    */
   public static byte[] readByte( String fileName ) throws ZycException 
   {
	   // 打开文件
	   InputStream	is = getResourceAsStream( fileName );
	   
	   // 读取数据
	   try{
		   return readFile( is );
	   }
	   catch( ZycException e ){
		   e.addMessage( "读取文件[" + fileName + "]的内容时错误" );
		   throw e;
	   }
	   finally{
		   try{
			   is.close();
		   }
		   catch( IOException e ){
			   log.error( "关闭文件时错误:" + fileName );
		   }
	   }
   }
   

   /**
    * 从文件中读取数据
    * @param fileName 文件名称
    * @return 文件内容
    * @throws ZycException
    */
   public static String readZipFile(String fileName) throws ZycException 
   {
	   return readZipFile( fileName, "GBK" );
   }
   
   
   /**
    * 读取文件内容
    * @param fileName 文件名称
    * @param charsetName 编码类型
    * @return 文件内容
    * @throws ZycException
    */
   public static String readZipFile(String fileName, String charsetName) throws ZycException 
   {
	   // 打开文件
	   InputStream	is = getResourceAsStream( fileName );
	   
	   // 读取数据
	   try{
		   return readZipFile( is, charsetName );
	   }
	   catch( ZycException e ){
		   e.addMessage( "读取文件[" + fileName + "]的内容时错误" );
		   throw e;
	   }
	   finally{
		   try{
			   is.close();
		   }
		   catch( IOException e ){
			   log.warn( "关闭文件时错误:" + fileName );
		   }
	   }
   }

   
   /**
    * 读取文件内容
    * @param file File对象
    * @param charsetName 编码类型
    * @return 文件内容
    * @throws ZycException
    */
   public static String readZipFile(File file, String charsetName) throws ZycException 
   {
	   // 打开文件
	   InputStream	is = getResourceAsStream( file );
	   
	   // 读取数据
	   try{
		   return readZipFile( is, charsetName );
	   }
	   catch( ZycException e ){
		   e.addMessage( "读取文件[" + file.getAbsolutePath() + "]的内容时错误" );
		   throw e;
	   }
	   finally{
		   try{
			   is.close();
		   }
		   catch( IOException e ){
			   log.error( "关闭文件时错误:" + file.getAbsolutePath() );
		   }
	   }
   }

   /**
    * 读取文件内容
    * @param is 输入流
    * @param charsetName 编码类型
    * @return 文件内容
    * @throws ZycException
    */
   public static String readZipFile(InputStream is, String charsetName) throws ZycException
   {
       int	count;
	   byte data[] = new byte[2048];
	   
	   try{
		   int off = 0;
		   GZIPInputStream zos = new GZIPInputStream( is );
		   ByteArrayOutputStream os = new ByteArrayOutputStream(10000);
		   while ((count = zos.read(data, off, 2048)) != -1) {
			   os.write(data, 0, count);
			   off += count;
		   }
		   
		   os.flush();
		   return os.toString( charsetName );
	   }
	   catch( IOException e ){
		   throw new ZycException ("FILE21", "读取压缩文件时错误");
	   }
   }
   
   
   
   /**
    * 取文件的CRC验证码
    * @param fileName 文件名称
    * @return 文件的CRC验证码
    * @throws ZycException
    */
   public static String getFileCRC32( String fileName ) throws ZycException
   {
	   InputStream is = getResourceAsStream( fileName );
	   return getFileCRC32( fileName, is );
   }

   /**
    * 取文件的CRC验证码
    * @param file File对象
    * @return 文件的CRC验证码
    * @throws ZycException
    */
   public static String getFileCRC32( File file ) throws ZycException
   {
	   InputStream is = getResourceAsStream( file );
	   return getFileCRC32( file.getAbsolutePath(), is );
   }
   
   /**
    * 取文件的CRC验证码
    * @param fileName 文件名称
    * @param is 文件的输入流
    * @return 文件的CRC验证码
    * @throws ZycException
    */
   public static String getFileCRC32( String fileName, InputStream is ) throws ZycException
   {
	   // 计算版本号
	   try{
		   byte data[] = readFile( is );
		   CRC32 crc = new CRC32();
		   crc.update( data );
		   long crc32 = crc.getValue();
		   return Long.toHexString( crc32 );
	   }
	   catch( ZycException e ){
		   e.addMessage( "计算文件[" + fileName + "]的CRC时错误" );
		   throw e;
	   }
	   catch( Exception e ){
		   throw new ZycException("FILE16", "计算文件的CRC时错误");
	   }
	   finally{
		   closeInputStream( is );
	   }
   }
   
   /**
    * 创建目录
    * @param path 目录名称
    * @return 目录全路径
    * @throws ZycException
    */
   public static String createPath( String path ) throws ZycException
   {
	   // 文件全路径
	   path = getFullName( path );
	   if( path == null ){
		   return null;
	   }
	   
	   try{
		   File	fpath = new File( path );
		   if( fpath.exists() == false ){
			   log.info( "创建目录：" + path );
			   boolean rc = fpath.mkdirs();
			   if( !rc ){
				   throw new ZycException("FILE12", "创建目录时错误");
			   }
		   }
		   
		   return path;
	   }
	   catch( ZycException e){
		   throw e;
	   }
	   catch( Throwable e ){
		   throw new ZycException("FILE12", "创建目录时错误");
	   }
   }
   
   /**
    * 生成文件
    * @param fileName 文件名称
    * @return File对象
    * @throws ZycException
    */
   public static File getFile( String fileName ) throws ZycException
   {
	   // 判断文件是否存在
	   File fp = new File(fileName);
	   if( fp.exists() ){
		   if( fp.canWrite() == false ){
			   setWritable( fileName );
		   }
		   
		   return fp;
	   }
	   
	   // 取文件目录并创建
	   boolean rc = true;
	   File	fpath = fp.getParentFile();
	   if( !fpath.exists() ){
		   try{
			   rc = fpath.mkdirs();
		   }
		   catch( Exception e){
			   throw new ZycException("FILE12", "创建目录时错误");
		   }
		   
		   if( !rc ){
			   throw new ZycException("FILE12", "创建目录时错误");
		   }
	   }
	   
	   return fp;
   }
   
   /**
    * 删除文件
    * @param fileName 文件名称
    * @throws ZycException
    */
   public static void deleteFile( String fileName ) throws ZycException 
   {
	   // 文件全路径
	   fileName = getFullName( fileName );
	   if( fileName == null ){
		   return;
	   }
	   
	   fileName = fileName.replace( '\\', '/' );
	   File fp = new File( fileName );
	   deleteFile( fp );
   }
   
   /**
    * 删除文件
    * @param file File对象
    * @throws ZycException
    */
   public static void deleteFile( File file ) throws ZycException 
   {
	   if( !file.exists() ){
		   return;
	   }
	   
	   // 删除文件 
	   try{
		   if( file.isDirectory() ){
			   // 删除所有子文件
			   File[] files = file.listFiles();
			   if( files != null ){
				   for( File f : files ){
					   deleteFile( f );
				   }
			   }
		   }
		   
		   boolean rc = file.delete();
		   if( !rc ){
			   file.deleteOnExit();
		   }
	   }
	   catch( Exception e ){
		   throw new ZycException("FILE08", "删除文件时错误");
	   }
   }

   /**
    * 删除目录下的所有文件
    * @param path
    * @throws ZycException
    */
   public static void deleteFiles( File path ) throws ZycException 
   {
	   if( path.exists() && path.isDirectory() ){
		   try{
			   File[] files = path.listFiles();
			   if( files != null ){
				   for( File f : files ){
					   f.delete();
				   }
			   }
		   }
		   catch( Throwable e ){
			   throw new ZycException("FILE08", "删除文件时错误");
		   }
	   }
   }
   
   /**
    * 检查文件是否存在
    * @param fileName 文件名称
    * @return true=文件存在
    */
   public static boolean exists( String fileName )
   {
	   // 文件全路径
	   fileName = getFullName( fileName );
	   if( fileName == null ){
		   return false;
	   }
	   
	   File fp = new File(fileName);
	   return fp.exists();
   }
   
   /**
    * JDK1.6以后，File对象提供这个功能
    * 设置文件可写
    * @param fileName 文件名称
    */
   public static void setWritable( String fileName )
   {
	   File file = new File(fileName);
	   file.setWritable( true );
   }
   
   /**
    * 设置文件可写
    * @param file File对象
    */
   public static void setWritable( File file )
   {
	   file.setWritable( true );
   }
   
   /**
    * 修改文件名称
    * 如果夸卷，先拷贝文件，然后删除
    * @param oldName 原文件名称
    * @param newName 新文件名称
    * @throws ZycException
    */
   public static void rename(String oldName, String newName) throws ZycException 
   {
	   // 修改文件名称
	   oldName = getFullName( oldName );
	   if( oldName == null ){
		   return;
	   }
	   
	   // 修改文件名称
	   newName = getFullName( newName );
	   if( newName == null ){
		   return;
	   }
	   
	   // 重命名文件名称
	   File	oldFile = new File( oldName );
	   File	newFile = new File( newName );
	   rename( oldFile, newFile );
   }

   /**
    * 修改文件名称
    * 如果夸卷，先拷贝文件，然后删除
    * @param oldFile 原文件对象
    * @param newFile 新文件对象
    * @throws ZycException
    */
   public static void rename(File oldFile, File newFile) throws ZycException 
   {
	   try{
		   if( !oldFile.exists() ){
			   log.error( "文件[" + oldFile.getAbsolutePath() + "]不存在" );
			   return;
		   }
		   
		   boolean rc = oldFile.renameTo( newFile );
		   if( !rc ){
			   copy( oldFile, newFile );
			   rc = oldFile.delete();
			   if( !rc ){
					log.info( "删除文件[" + oldFile.getAbsolutePath() + "]时错误:" + rc );
			   }
		   }
	   }
	   catch( ZycException e ){
		   throw e;
	   }
	   catch( Exception e ){
		   throw new ZycException("FILE14", "修改文件名称时错误");
	   }
   }
   
   /**
    * 拷贝文件
    * @param fromFile 原文件
    * @param toFile 目标文件
    * @throws ZycException
    */
   public static void copy( String fromFile, String toFile ) throws ZycException 
   {
	   copy( fromFile, toFile, false );
   }

   /**
    * 拷贝文件
    * @param from 原文件对象
    * @param to 目标文件对象
    * @throws ZycException
    */
   public static void copy( File from, File to ) throws ZycException 
   {
	   copy( from, to, false );
   }

   /**
    * 复制文件或目录
    * @param fromFile 源文件目录或文件名称
    * @param toFile 目标文件目录或文件名称
    * @param checkTime 是否检查文件的时间，如果不检查时间，只要文件存在，就不覆盖
    * @throws ZycException
    */
   public static void copy( String fromFile, String toFile,
		   boolean checkTime ) throws ZycException 
   {
	   // 打开文件
	   if( fromFile == null || fromFile.length() == 0 ){
		   throw new ZycException("FILE14", "修改文件名称时错误");
	   }
	   
	   // 取文件路径
	   String resourceName = null;
	   if( fromFile.startsWith(CLASSES_PATHNAME) ){
		   resourceName = fromFile.substring( CLASSES_PATHNAME.length() );
	   }
	   
	   // 取文件信息
	   InputStream is = null;
	   File from = new File( fromFile );
	   File to = new File( toFile );
	   
	   if( from.exists() ){
		   // 复制目录
		   if( from.isDirectory() ){
			   copyPath( fromFile, toFile, checkTime );
			   return;
		   }
		   
		   // 检查目标文件的时间
		   if( checkTime && to.exists() ){
			   if( from.lastModified() <= to.lastModified() ){
				   return;
			   }
		   }
		   
		   try{
			   is = new FileInputStream( from );
		   }
		   catch(FileNotFoundException e){
			   throw new ZycException("FILE01", "文件不存在");
		   }
	   }
	   else{
		   // 打开文件
		   if( resourceName != null ){
			   try{
				   is = FileUtil.class.getResourceAsStream(resourceName);
			   }
			   catch( Exception e ){
				   throw  new ZycException("FILE01", "文件不存在");
			   }
		   }
		   else{
			   // 打开本地文件
			   try{
				   is = FileUtil.class.getResourceAsStream(fromFile);
			   }
			   catch( Exception e ){
				   throw new ZycException("FILE01", "文件不存在");
			   }
		   }
	   }
	   
	   // 文件不存在
	   if( is == null ){
		   throw new ZycException("FILE01", "文件不存在");
	   }
	   
	   // 生成目标文件
	   OutputStream	os = null;
	   try{
		   os = openWriteFile( toFile );
		   copy( is, os );
	   }
	   catch( ZycException e ){
		   e.addMessage( "拷贝文件[" + fromFile + "] --> [" + toFile + "]" );
	   }
	   finally{
		   closeInputStream( is );
		   closeOutputStream( os );
	   }
	   
	   // 设置修改时间
	   // if( from.exists() ){
	   //    to.setLastModified( from.lastModified() );
	   // }
   }

   /**
    * 复制文件或目录
    * @param from 源文件目录或文件名称
    * @param to 目标文件目录或文件名称
    * @param checkTime 是否检查文件的时间，如果不检查时间，只要文件存在，就不覆盖
    * @throws ZycException
    */
   public static void copy( File from, File to,
		   boolean checkTime ) throws ZycException 
   {
	   // 复制目录
	   if( from.isDirectory() ){
		   copyPath( from, to, checkTime );
		   return;
	   }
	   
	   // 检查目标文件的时间
	   if( checkTime && to.exists() ){
		   if( from.lastModified() <= to.lastModified() ){
			   return;
		   }
	   }

	   // 打开的文件句柄
	   InputStream is = null;
	   try{
		   is = new FileInputStream( from );
	   }
	   catch(FileNotFoundException e){
		   throw new ZycException("FILE01", "文件不存在");
	   }
	   
	   // 生成目标文件
	   OutputStream	os = null;
	   try{
		   os = openWriteFile( to );
		   copy( is, os );
	   }
	   catch( ZycException e ){
		   e.addMessage( "拷贝文件[" + from.getAbsolutePath() + "] --> [" + to.getAbsolutePath() + "]" );
	   }
	   finally{
		   closeInputStream( is );
		   closeOutputStream( os );
	   }
	   
	   // 设置修改时间
	   // if( from.exists() ){
	   //    to.setLastModified( from.lastModified() );
	   // }
   }
   
   /**
    * 复制目录
    * @param fromPath 源文件目录
    * @param toPath 目标文件目录
    * @param checkTime 是否检查文件的时间，如果不检查时间，只要文件存在，就不覆盖
    * @throws ZycException
    */
   private static void copyPath( String fromPath, String toPath,
		   boolean checkTime ) throws ZycException
   {
	   // 格式化目录
	   toPath = toPath.replace( '\\', '/' );
	   if( toPath.endsWith("/") == false ){
		   toPath = toPath + "/";
	   }
	   
	   // 复制文件
	   File fileList[] = (new File(fromPath)).listFiles();
	   if( fileList == null ){
		   return;
	   }
	   
	   for( File from : fileList ){
		   // 目录不处理
		   if( from.isFile() == false ){
			   continue;
		   }
		   
		   // 检查文件时间
		   String toFile = toPath + from.getName();
		   File to = new File( toFile );
		   if( checkTime && to.exists() ){
			   if( from.lastModified() <= to.lastModified() ){
				   continue;
			   }
		   }
		   
		   InputStream is = null;
		   try{
			   is = new FileInputStream( from );
		   }
		   catch(FileNotFoundException e){
			   throw new ZycException("FILE01", "文件不存在");
		   }
		   
		   // 生成目标文件
		   OutputStream	os = null;
		   try{
			   os = openWriteFile( toFile );
			   copy( is, os );
		   }
		   catch( ZycException e ){
			   e.addMessage( "拷贝文件[" + from.getAbsolutePath() + "] --> [" + toFile + "]" );
		   }
		   finally{
			   closeInputStream( is );
			   closeOutputStream( os );
		   }
		   
		   // 设置修改时间
		   // to.setLastModified( from.lastModified() );
	   }
   }

   /**
    * 复制目录
    * @param fromPath 源文件目录
    * @param toPath 目标文件目录
    * @param checkTime 是否检查文件的时间，如果不检查时间，只要文件存在，就不覆盖
    * @throws ZycException
    */
   private static void copyPath( File fromPath, File toPath,
		   boolean checkTime ) throws ZycException
   {
	   String destPath = toPath.getAbsolutePath() + "/";
	   File fileList[] = fromPath.listFiles();
	   if( fileList == null ){
		   return;
	   }
	   
	   for( File from : fileList ){
		   // 目录不处理
		   if( from.isFile() == false ){
			   continue;
		   }
		   
		   // 检查文件时间
		   String toFile = destPath + from.getName();
		   File to = new File( toFile );
		   if( checkTime && to.exists() ){
			   if( from.lastModified() <= to.lastModified() ){
				   continue;
			   }
		   }
		   
		   InputStream is = null;
		   try{
			   is = new FileInputStream( from );
		   }
		   catch(FileNotFoundException e){
			   throw  new ZycException("FILE01", "文件不存在");
		   }
		   
		   // 生成目标文件
		   OutputStream	os = null;
		   try{
			   os = openWriteFile( toFile );
			   copy( is, os );
		   }
		   catch( ZycException e ){
			   e.addMessage( "拷贝文件[" + from.getAbsolutePath() + "] --> [" + toFile + "]" );
			   throw e;
		   }
		   finally{
			   closeInputStream( is );
			   closeOutputStream( os );
		   }
		   
		   // 设置修改时间
		   // to.setLastModified( from.lastModified() );
	   }
   }
   
   /**
    * 拷贝文件
    * @param is 原文件的输入流
    * @param toFile 目标文件
    * @throws ZycException
    */
   public static void copy(InputStream is, String toFile) throws ZycException 
   {
	   // 打开文件
	   OutputStream	os = openWriteFile( toFile );
	   
	   try{
		   copy( is, os );
	   }
	   catch( ZycException e ){
		   e.addMessage( "拷贝文件 --> [" + toFile + "]" );
		   throw e;
	   }
	   finally{
		   FileUtil.closeInputStream(is);
		   FileUtil.closeOutputStream(os);
	   }
   }
   
   /**
    * 拷贝文件
    * @param is 原文件的输入流
    * @param toFile 目标文件对象
    * @throws ZycException
    */
   public static void copy(InputStream is, File toFile) throws ZycException 
   {
	   // 打开文件
	   OutputStream	os = openWriteFile( toFile );
	   
	   try{
		   copy( is, os );
	   }
	   catch( ZycException e ){
		   e.addMessage( "拷贝文件 --> [" + toFile + "]" );
	   }
	   finally{
		   FileUtil.closeInputStream(is);
		   FileUtil.closeOutputStream(os);
	   }
   }
   
   /**
    * 拷贝文件
    * @param is 原文件的输入流
    * @param os 目标文件输出流
    * @throws ZycException
    */
   public static void copy( InputStream is, OutputStream os ) throws ZycException
   {
	   try{
		   // 读取数据
		   byte	buffer[] = new byte[4097];
		   int readLength = is.read( buffer, 0, 4096 );
		   
		   while( readLength > 0 ){
			   os.write( buffer, 0, readLength );
			   readLength = is.read( buffer, 0, 4096 );
		   }
	   }
	   catch( IOException e ){
		   throw new ZycException("FILE03", "读取文件时错误");
	   }
   }

   /**
    * 拷贝目录
    * @param fromPath 源文件目录
    * @param toPath 目标文件目录
    * @throws ZycException
    */
   public static void xcopy( String fromPath, String toPath ) throws ZycException 
   {
	   xcopy( fromPath, toPath, false );
   }

   /**
    * 拷贝目录
    * @param fromPath 源文件目录
    * @param toPath 目标文件目录
    * @param checkTime 是否检查文件的时间，如果不检查时间，只要文件存在，就不覆盖
    * @throws ZycException
    */
   public static void xcopy(String fromPath, String toPath,
		   boolean checkTime) throws ZycException 
   {
	   fromPath = getFullName( fromPath );
	   if( fromPath == null ){
		   return;
	   }
	   
	   toPath = getFullName( toPath );
	   if( toPath == null ){
		   return;
	   }
	   
	   // 判断文件是否存在
	   File fp = new File( fromPath );
	   if( fp.exists() == false ){
		   throw new ZycException("FILE01", "文件不存在");
	   }
	   
	   // 判断是目录还是文件
	   if( fp.isDirectory() ){
		   // 格式化目录
		   fromPath = fromPath.replace( '\\', '/' );
		   if( fromPath.endsWith("/") ){
			   fromPath = fromPath.substring(0, fromPath.length()-1);
		   }
		   
		   toPath = toPath.replace( '\\', '/' );
		   if( toPath.endsWith("/") ){
			   toPath = toPath.substring(0, toPath.length()-1);
		   }
		   
		   // 复制目录
		   xcopy1( fromPath, toPath, checkTime );
	   }
	   else{
		   // 拷贝文件
		   xcopy1( fromPath, toPath, checkTime );
	   }
   }

   private static void xcopy1(String fromPath, String toPath,
		   boolean checkTime) throws ZycException 
   {
	   // 判断文件是否存在
	   File fp = new File( fromPath );
	   if( fp.exists() == false ){
		  throw new ZycException("FILE01", "文件不存在");
	   }
	   
	   // 判断是目录还是文件
	   if( fp.isDirectory() ){
		   // 创建目标目录
		   createPath( toPath );
		   
		   // 格式化目录
		   fromPath = fromPath + "/";
		   toPath = toPath + "/";
		   
		   // 文件列表
		   String[]	fileList = fp.list();
		   if( fileList != null ){
			   for( int ii=0; ii<fileList.length; ii++ ){
				   xcopy1( fromPath + fileList[ii], toPath + fileList[ii], checkTime );
			   }
		   }
	   }
	   else{
		   // 拷贝文件
		   File pfn = new File( toPath );
		   if( pfn.exists() ){
			   // 判断时间
			   if( checkTime && fp.lastModified() > pfn.lastModified() ){
				   copy( fromPath, toPath );
			   }
		   }
		   else{
			   copy( fromPath, toPath );
		   }
	   }
   }
   
   /**
    * 取文件列表
    * @param pathName 目录名称
    * @param extName 文件扩展名
    * @return 文件名称列表
    * @throws ZycException
    */
   public static String[] getFileList(String pathName, String extName)
   {
	   String list[] = null;
	   
	   // 取目录
	   try{
		   pathName = getFullName( pathName );
		   if( pathName == null ){
			   return null;
		   }
		   
		   File path = new File( pathName );
		   
		   // 检索文件
		   if( extName != null && 
				   extName.length() != 0 && 
				   extName.compareTo("*") != 0 ){
			   list = path.list( new FileFilter(extName) );
		   }
		   else{
			   list = path.list( );
		   }
	   }
	   catch( Throwable e ){
		   log.error( "加载目录[" + pathName + "]的文件列表时错误" );
	   }
	   
	   return list;
   }
   
   /**
    * 取目录下的文件列表
    * @param pathName 目录名称
    * @param extList 文件后缀数组
    * @return File对象列表
    */
   public static File[] listFiles( String pathName, String... extList )
   {
	   pathName = getFullName( pathName );
	   if( pathName == null ){
		   return null;
	   }
	   
	   return listFiles( new File(pathName), extList );
   }

   /**
    * 取目录下的文件列表
    * @param path 目录名称
    * @param extList 文件后缀数组
    * @return File对象列表
    */
   public static File[] listFiles( File path, String... extList )
   {
	   File list[] = null;
	   
	   try{
		   int l = extList.length;
		   if( l == 0 ){
			   list = path.listFiles( );
		   }
		   else if( l == 1 ){
			   String extName = extList[0];
			   if( extName != null &&
					   extName.compareTo("*") != 0 &&
					   extName.compareTo("*.*") != 0 )
			   {
				   list = path.listFiles( new FileFilter(extName) );
			   }
			   else{
				   list = path.listFiles( );
			   }
		   }
		   else{
			   list = path.listFiles( new FileFilter(extList) );
		   }
	   }
	   catch( Throwable e ){
		   log.error( "加载文件[" + path.getPath() + "]的目录结构时错误", e );
	   }
	   
	   return list;
   }
	
	/**
	 * 取全路径名称
	 * 文件名称可以是全路径，以及/WEB-INF、/classes、$HOME、$resource、$share、$project等前缀
	 * @param fileName 文件名称
	 * @return 全路径的文件名称
	 */
	public static String getFullName( String fileName )
	{
		if( fileName == null || fileName.length() == 0 ){
			return null;
		}
		
		return fileName;
	}
	
	/**
	 * 格式化文件名称
	 * 主要是转换空格
	 * @param fileName 文件名称
	 * @return 格式化后的文件名称
	 */
	public static String formatFileName( String fileName )
	{
		char content[] = new char[fileName.length()];
		char result[] = new char[fileName.length()];
		fileName.getChars(0, fileName.length(), content, 0);
		
		// 格式化文件名称
		int len = 0;
		for (int i = 0; i < content.length; i++) {
			if( content[i] == '%' && content[i+1] == '2' && content[i+2] == '0' ){
				result[len++] = ' ';
				i = i + 2;
			}
			else{
				result[len++] = content[i];
			}
		}
		
		return String.valueOf( result, 0, len );
	}
	
	/**
	 * 生成临时文件名称
	 * @return 临时文件名称
	 */
	public static String getTempFileName( )
	{
		return cn.chzu.conf.util.PathPool.getTempFilePath() + "tmp" + cn.chzu.conf.util.UUID.getUUID();
	}
	
	/**
	 * 生成临时文件名称
	 * @param ext 文件后缀
	 * @return 临时文件名称
	 */
	public static String getTempFileName( String ext )
	{
		if( ext.charAt(0) != '.' ){
			return cn.chzu.conf.util.PathPool.getTempFilePath() + "tmp" + cn.chzu.conf.util.UUID.getUUID() + '.' + ext;
		}
		else{
			return cn.chzu.conf.util.PathPool.getTempFilePath() + "tmp" + cn.chzu.conf.util.UUID.getUUID() + ext;
		}
	}

	
	/**
	 * 取临时文件目录
	 * @return
	 */
	public static String getTempPath()
	{
		String path = System.getProperty("java.io.tmpdir");
		if( path != null && !path.isEmpty() ){
			return path;
		}
		
		Map<String, String> map = System.getenv();  
		String str = map.get( "TEMP" );
		if( str == null || str.isEmpty() ){
			str = map.get( "TMP" );
			if( str == null || str.isEmpty() ){
				str = "d:\\temp";
				File fp = new File( str );
				if( !fp.exists() ){
					fp.mkdirs();
				}
			}
		}
		
		return str;
	}
	
	/**
	 * 关闭文件
	 * @param os 输出流
	 */
	public static void closeOutputStream( OutputStream os )
	{
		try{
			if( os != null ){
				os.close();
			}
		}
		catch( Throwable e ){
			log.error( "关闭文件时错误", e );
		}
	}
	
	/**
	 * 关闭文件
	 * @param is 输入流
	 */
	public static void closeInputStream( InputStream is )
	{
		try{
			if( is != null ){
				is.close();
			}
		}
		catch( Throwable e ){
			log.error( "关闭文件时错误", e );
		}
	}
	
	public static void main(String[] args)
	{
		/*try{
			copy( "C:/bea/user_projects/domains/gwssi/applications/beacon/WEB-INF", "C:/bea/demo/temp", true );
		}
		catch( Exception e ){
			System.out.println( "拷贝文件时错误" );
			e.printStackTrace();
		}*/
		
		String fileName = "D:/Documents and Settings/Administrator/桌面/aaaa.txt";
		
		try{
			InputStream is = getResourceAsStream( fileName );
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			
			String str = in.readLine();
			while( str != null ){
				str = str.trim();
				if( str.length() == 0 ){
					continue;
				}
				
				str = str.substring(str.length()-3) + ";" + str.substring(0, str.length()-3);
				System.out.println( str );
				
				str = in.readLine();
			}
		}
		catch( Exception e ){
			System.out.println( "处理文件时错误" );
			e.printStackTrace();
		}
	}
	
	/**
	 * @功能描述: 以工作空间编码写文本文件
	 */
	public static void writeFile(String path, String context) {
		createDir(path);
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
			writer.write(context);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @功能描述:如果上层文件平不存在创建文件夹
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}
}

