// import { HOST_NAME } from '@/constants';
// import { getJwtToken } from '@/utils/cache';

// export const sse = new EventSource(`${HOST_NAME}/system/info/memory?token=${getJwtToken()}`)
const useMemory = () => {
  // sse.onopen = () => {
  //   console.log("on event open :::")
  // }
  // sse.onmessage = (event) => {
  //   console.log("msg event: ", event.data);
  // }
  return {}
}

export default useMemory