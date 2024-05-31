import {request, useRequest} from "@@/exports";
import {Result} from "@/types/result";

export interface MyTextModel {
    id?: number,
    name?: string,
    intro?: string,
    context?: string,
    isEncryptionText?: boolean,
    viewPassword?: string,
    originPassword?: string,
}

///获取所有字典
async function getAllText():Promise<Result<MyTextModel[]>>{
    return request('/api/blog/getTextAll',{
        method: 'GET'
    })
}

///模型
export default function useTextModel() {
    const {loading,data: allTexts} = useRequest(()=>getAllText())
    return {
        loading,allTexts
    }
}
