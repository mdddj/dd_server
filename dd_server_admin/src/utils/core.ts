export function isValidHttpUrl(url: string): boolean {
    const pattern = /^(https?):\/\/[-A-Za-z0-9+&@#\/%?=~_|!:,.;]*[-A-Za-z0-9+&@#\/%=~_|]$/;
    return pattern.test(url);
}