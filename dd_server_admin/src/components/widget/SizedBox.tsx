type SizedBoxProp = {
  height?: number | undefined;
  width?: number | undefined;
};

const SizedBox: React.FC<SizedBoxProp> = ({ height, width }) => {
  return <div style={{ height: height ?? 12, width: width ?? 12 }}></div>;
};

export default SizedBox;
