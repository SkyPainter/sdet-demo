package com.hcg.framework.exel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileDriver 
{
	private File             excelFile;
	private FileInputStream  fileInputStream;
	private FileOutputStream fileOutputStream;
	private String           excelFilename;
	private XSSFWorkbook     workbook;
	
	public void openXlsxFile(String argFilename, boolean argCreateFile)
	{
		String fileExtension = null;
		
		try
		{
			if (argFilename != null)
			{
				fileExtension = argFilename.substring(argFilename.indexOf("."));
				
				if (".xlsx".equalsIgnoreCase(fileExtension))
				{

					excelFilename = argFilename;
					excelFile     = new File(argFilename);
						
					if (excelFile.exists())
					{
						fileInputStream = new FileInputStream(excelFile);
						workbook = new XSSFWorkbook(fileInputStream);
					}
					else
					{
						if (argCreateFile)
						{
							excelFile.createNewFile();
							
							fileOutputStream = new FileOutputStream(excelFile);
							
							workbook = new XSSFWorkbook();
							workbook.write(fileOutputStream);
							workbook.close();
							fileOutputStream.close();
							
							fileInputStream = new FileInputStream(excelFile);
							workbook = new XSSFWorkbook(fileInputStream);
						}
						else
							throw new FileNotFoundException("File \"" + argFilename + "\" does not exist.");
					}
				}
				else
				{
					throw new Exception("Invalid file extension \"" + fileExtension + "\". Make sure file has .xlsx extension.");
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (EncryptedDocumentException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void save() 
	{
		try 
		{
			fileOutputStream = new FileOutputStream(excelFilename);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void saveAs(String argNewFilename)
	{
		File newFile;
		FileOutputStream newFileOutputStream;
		
		try 
		{
			argNewFilename = argNewFilename.trim();
			
			if (argNewFilename.isEmpty())
			{
				throw new Exception("File name is empty.");
			}
			
			newFile = new File(argNewFilename);
			
			if (newFile.exists())
			{
				throw new Exception("File already exists");
			}
			
			
			newFileOutputStream = new FileOutputStream(newFile);
			workbook.write(newFileOutputStream);
			newFileOutputStream.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void close() 
	{
		try 
		{
			if (fileInputStream != null)
				fileInputStream.close();
			
			if (fileOutputStream != null)
				fileOutputStream.close();
			
			if (workbook != null)
				workbook.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void createSheet(String argSheetName) 
	{
		Sheet sheet;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty())
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			if (sheet != null)
			{
				throw new Exception("Sheet Already exists.");
			}
			
			workbook.createSheet(argSheetName);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public int getSheetRowCount(String argSheetName) 
	{
		Sheet sheet;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty())
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			if (sheet == null)
			{
				throw new Exception("Sheet does not exist.");
			}
			
			return(sheet.getLastRowNum());
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return(-1);
		}
	}
	
	public int getCellCount(String argSheetName, int argRow) 
	{
		Sheet sheet;
		Row   row;
		
		try
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty()) 
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			if (sheet == null) 
			{
				throw new Exception("Sheet does not exist.");
			}
			
			if (argRow < 1)
			{
				throw new Exception("Row indices must start from 1");
			}
			
			row = sheet.getRow(argRow);
			
			if (row == null ) 
			{
				return(0);
			} 
			else 
			{
				return(row.getLastCellNum());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return(-1);
		}
	}
	
	public int getSheetLastRow(String argSheetName)
	{
		Sheet sheet;
		int   lastRow;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty()) 
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			if (sheet == null) 
			{
				throw new Exception("Sheet does not exist.");
			}
			
			lastRow = sheet.getLastRowNum() + 1;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return(-1);
		}
		
		return(lastRow);
	}
	
	public String getCellData(String argSheetName, int argRow, int argCol)
	{
		Sheet sheet;
		Row   row;
		Cell  cell;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty()) 
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			if (sheet == null) 
			{
				throw new Exception("Sheet does not exist.");
			}
			
			if (argRow < 1 || argCol < 1) 
			{
				throw new Exception("Row and cell indices must start from 1");
			}
			
			row = sheet.getRow(argRow-1);
			
			if (row == null ) 
			{
				return("");
			} 
			
			cell = row.getCell(argCol-1);
			
			if (cell == null ) 
			{
				return("");
			} 
			else 
			{
				if (cell.getCellTypeEnum() == CellType.NUMERIC)
				{
					return(String.valueOf((int) cell.getNumericCellValue()));
				} 
				else 
				{
					return(cell.getStringCellValue());
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return("");
		}
	}
	
	public void setCellData(String argSheetName, int argRow, int argCol, String argValue)
	{
		Sheet sheet;
		Row   row;
		Cell  cell;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty()) 
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			if (sheet == null) 
			{
				sheet = workbook.createSheet(argSheetName);
			}
			
			if (argRow < 1 || argCol < 1) 
			{
				throw new Exception("Row and cell indices must start from 1");
			}
			
			row = sheet.getRow(argRow-1);
			
			if (row == null) 
			{
				sheet.createRow(argRow-1);
				row = sheet.getRow(argRow-1);
			} 
			
			cell = row.getCell(argCol-1);
			
			if (cell == null ) 
			{
				row.createCell(argCol-1);
				cell = row.getCell(argCol-1);
			}  
			
			cell.setCellValue(argValue);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteRow(String argSheetName, int argRow)
	{
		Sheet sheet;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty())
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			deleteRow(sheet, argRow);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteRow(Sheet argSheet, int argRow)
	{
		int lastRow;
		
		try 
		{
			if (argSheet == null)
			{
				throw new Exception("Sheet does not exist.");
			}
			
			lastRow = argSheet.getLastRowNum() + 1;
			
			if ((argRow >= 1) && (argRow < lastRow))
			{
				argSheet.shiftRows(argRow, lastRow - 1, -1);
			}
			
			if (argRow == lastRow)
			{
				Row rowToBeRemoved = argSheet.getRow(argRow - 1);
				
				if (rowToBeRemoved != null)
				{
					argSheet.removeRow(rowToBeRemoved);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteRows(String argSheetName, int argFromRow, int argToRow)
	{
		Sheet sheet;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty())
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			deleteRows(sheet, argFromRow, argToRow);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteRows(Sheet sheet, int argFromRow, int argToRow)
	{
		int lastRow;
		
		try 
		{
			if (sheet == null)
			{
				throw new Exception("Sheet does not exist.");
			}
			
			lastRow = sheet.getLastRowNum() + 1;
			
			if (((argFromRow >= 0) && (argFromRow <= lastRow)) &&
				((argToRow >= 0) && (argToRow <= lastRow)) &&
				(argFromRow <= argToRow))
			{
				for (int row=argToRow; row >= argFromRow; row--)
				{
					this.deleteRow(sheet, row);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteAllRows(String argSheetName)
	{
		Sheet sheet;
		
		try 
		{
			argSheetName = argSheetName.trim();
			
			if (argSheetName.isEmpty())
			{
				throw new Exception("Sheet name is empty.");
			}
			
			sheet = workbook.getSheet(argSheetName);
			
			if (sheet != null)
			{
				deleteRows(sheet, 1, sheet.getLastRowNum() + 1);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getColumnHeader(String argSheetName, int argCol)
	{
		return(getCellData(argSheetName, 1, argCol));
	}
	
	public void setColumnHeader(String argSheetName, int argCol, String argValue)
	{
		setCellData(argSheetName, 1, argCol, argValue);
	}
	
}

