import {ProFormSelect, ProFormSelectProps} from "@ant-design/pro-components";
import {useModel} from "@@/exports";
import React from "react";


///字典选择器表单
const TextProFormSelect: React.FC<ProFormSelectProps> = (props) => {
    const {allTexts} = useModel("useText")
    return <ProFormSelect width={'sm'} {...props} options={allTexts?.map(item => {
        return {
            label: item.name, value: item.name
        }
    }) ?? []}>

    </ProFormSelect>
}
export default TextProFormSelect