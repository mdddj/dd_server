import {request} from "@@/plugin-request";
import {MinioDetailModel} from "@/services/minio/MinioModel";
import {Result} from "@/types/result";

export async function getMinioConfig() : Promise<Result<MinioDetailModel>>{
    return request<Result<MinioDetailModel>>('/admin/minio/get',{
        method: 'GET'
    })
}


export async function updateMinioConfig(model: MinioDetailModel) :  Promise<Result<MinioDetailModel>> {
    return request('/admin/minio/update',{
        method: 'POST',
        data: model
    })
}