import api from './api';

const getReceiptsByUserId = (userId) => api.get(`/receipt/get-all-by-user/${userId}`);

const receiptService = { getReceiptsByUserId };
export default receiptService;
