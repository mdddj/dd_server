import { CoverToPageData, PageModel, Result } from '@/types/result';
import { ProColumns, ProTable } from '@ant-design/pro-components';
import React from 'react';

type ApiTableProp<T extends Record<string, any>> = {
  api: (params: PageModel) => Promise<Result<CoverToPageData<T>>>;
  columns?: ProColumns<T>[];
  rowKey?: string;
};
export default class ApiTable<
  T extends Record<string, any>,
> extends React.Component<ApiTableProp<T>> {
  render() {
    return (
      <ProTable<T>
        rowKey={this.props.rowKey}
        request={async (params) => {
          let result = await this.props.api({
            page: (params.current ?? 1) - 1,
            pageSize: params.pageSize ?? 20,
          });
          return {
            success: result.success,
            total: result.data.page.total,
            data: result.data.list,
          };
        }}
        columns={this.props.columns}
      />
    );
  }
}
