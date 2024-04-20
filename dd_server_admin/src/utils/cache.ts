export function getJwtToken(): string | null {
  return window.localStorage.getItem('token');
}

export function removeJwtToken() {
  window.localStorage.removeItem('token');
}

export function setJwtToken(token: string) {
  removeJwtToken();
  window.localStorage.setItem('token', token);
}
