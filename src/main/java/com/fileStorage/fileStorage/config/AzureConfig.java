package com.fileStorage.fileStorage.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobErrorCode;
import com.azure.storage.blob.models.BlobStorageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AzureConfig
{
    private static final String bucketName="image-site-bucket";
    private static final String containerString="<insert>";

    @Bean
    public BlobContainerClient blob()
    {
        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(containerString).
                buildClient();

        BlobContainerClient containerClient=null;
        try
        {
            containerClient = blobServiceClient.getBlobContainerClient(bucketName);

            if(!containerClient.exists())
            containerClient = blobServiceClient.createBlobContainer(bucketName);

        }
        catch (BlobStorageException ex)
        {
            // The container may already exist, so don't throw an error
            if (!ex.getErrorCode().equals(BlobErrorCode.CONTAINER_ALREADY_EXISTS))
                throw ex;
        }
        return containerClient;
    }
}
