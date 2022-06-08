package com.fileStorage.fileStorage.fileStorage;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

@Service
public class SaveFile
{
    private final BlobContainerClient containerClient;

    @Autowired
    public SaveFile(BlobContainerClient a)
    {
        this.containerClient=a;
    }

    public void save(String uploadFileName, InputStream inputStream, Map<String,String> metadata)
    {
        try
        { //Filename needs extension
            BlobClient blobClient = containerClient.getBlobClient(uploadFileName);
            blobClient.upload(BinaryData.fromStream(inputStream),true);
        }
        catch(Exception e)
        {
            System.out.println("SaveFile Exception");
            e.printStackTrace();
            throw e;
        }
    }

    public byte[] download(String blobName)
    {
        try {
            BlobClient blobClient = containerClient.getBlobClient(blobName);
//            System.out.println("Down "+blobName);
//            for (BlobItem blobItem : containerClient.listBlobs())
//            {
//                System.out.println("This is the blob name: " + blobItem.getName());
//            }
            if (!blobClient.exists())
            {
                System.out.println("Blob dosent exist+ "+blobName);
                return new byte[0];
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            blobClient.download(outputStream);
            final byte[] bytes =outputStream.toByteArray();
            System.out.println(bytes.length + " Downloaded in save file");
            return bytes;
        }
        catch(Exception e)
        {
            throw new IllegalStateException("Failed to download");
        }
    }
}
