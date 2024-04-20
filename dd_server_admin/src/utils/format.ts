// 示例方法，没有实际意义
export function trim(str: string) {
  return str.trim();
}

export const objectToFormData = (obj: { [key: string]: any }): FormData => {
  const formData = new FormData();
  for (const key in obj) {
    if (obj.hasOwnProperty(key)) {
      formData.append(key, obj[key]);
    }
  }
  return formData;
};
