import { request, useRequest } from '@umijs/max';
import React from 'react';
import { Card, CardBody, CardHeader, Divider } from '@nextui-org/react';
import { Result } from '@/types/result';


interface SystemJavaInfo {
  data: Data;
  message: string;
  redirectUrl: string;
  state: number;
  success: boolean;
  type: string;
}

export interface Data {
  hostInfo: HostInfo;
  javaInfo: JavaInfo;
  javaRuntimeInfo: JavaRuntimeInfo;
  javaSpecInfo: Info;
  jvmInfo: Info;
  jvmSpecInfo: Info;
  osInfo: OSInfo;
  runtimeInfo: RuntimeInfo;
  userInfo: UserInfo;
}

interface HostInfo {
  address: string;
  name: string;
}

interface JavaInfo {
  java10: boolean;
  java11: boolean;
  java12: boolean;
  java13: boolean;
  java14: boolean;
  java15: boolean;
  java16: boolean;
  java17: boolean;
  java18: boolean;
  java1_1: boolean;
  java1_2: boolean;
  java1_3: boolean;
  java1_4: boolean;
  java1_5: boolean;
  java1_6: boolean;
  java1_7: boolean;
  java1_8: boolean;
  java9: boolean;
  vendor: string;
  vendorURL: string;
  version: string;
  versionFloat: number;
  versionInt: number;
}

interface JavaRuntimeInfo {
  classPath: string;
  classPathArray: string[];
  classVersion: string;
  endorsedDirs: string;
  extDirs: string;
  homeDir: string;
  libraryPath: string;
  libraryPathArray: string[];
  name: string;
  protocolPackages: string;
  sunArchDataModel: string;
  sunBoothClassPath: string;
  version: string;
}

interface Info {
  name: string;
  vendor: string;
  version: string;
  info?: string;
}

interface OSInfo {
  aix: boolean;
  arch: string;
  fileSeparator: string;
  hpUx: boolean;
  irix: boolean;
  lineSeparator: string;
  linux: boolean;
  mac: boolean;
  macOsX: boolean;
  name: string;
  os2: boolean;
  pathSeparator: string;
  solaris: boolean;
  sunOS: boolean;
  version: string;
  windows: boolean;
  windows10: boolean;
  windows2000: boolean;
  windows7: boolean;
  windows8: boolean;
  windows8_1: boolean;
  windows95: boolean;
  windows98: boolean;
  windowsME: boolean;
  windowsNT: boolean;
  windowsXP: boolean;
}

interface RuntimeInfo {
  freeMemory: number;
  maxMemory: number;
  runtime: Runtime;
  totalMemory: number;
  usableMemory: number;
}

interface Runtime {
}

interface UserInfo {
  country: string;
  currentDir: string;
  homeDir: string;
  language: string;
  name: string;
  tempDir: string;
}

async function getSystemInfo(): Promise<Result<SystemJavaInfo>> {
  return request<Result<SystemJavaInfo>>('/system/info/get', {
    method: 'GET',
  });
}

const SystemJavaInfoCard: React.FC = () => {
  const { data, loading } = useRequest<SystemJavaInfo>(() => getSystemInfo());
  return <Card>
    <CardHeader>
      <h4 className={'font-bold text-lg'}>系统信息</h4>
    </CardHeader>
    <Divider />
    <CardBody>
      {loading && <div>加载中</div>}
      {
        data && <div className={'flex flex-col gap-2'}>

          <InfoItem title={'java版本'} value={data.javaInfo.version + `(${data?.javaSpecInfo.vendor})`} />
          <InfoItem title={'jvm'} value={`${data?.jvmInfo.name}`} />
          <InfoItem title={'系统类型'} value={`${data?.osInfo.name} ${data?.osInfo.version} ${data?.osInfo.arch}`} />
          <InfoItem title={'主机名称'} value={`${data?.userInfo.name}`} />
        </div>
      }
    </CardBody>
  </Card>;
};

const InfoItem: React.FC<{ title: string, value: string }> = (props) => {
  return <div className={'flex flex-row gap-2'}>
    <div className={'font-bold w-32'}>{props.title}</div>
    <div>{props.value}</div>
  </div>;
};

export default SystemJavaInfoCard;