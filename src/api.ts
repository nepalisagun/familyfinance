export interface FinanceRecord {
  id?: number;
  date: string;
  type: string;
  amount: number;
  source?: string;
  category?: string;
  tag?: string;
  targetAccount?: string;
  fromAccount?: string;
  toAccount?: string;
  frequency?: string;
  isFixedExpense?: boolean;
  workspace: string;
}

export interface ApiResponse<T> {
  data: T;
  error?: string;
}

const API_URL = import.meta.env.VITE_API_URL || "http://localhost:3200/api";

export async function fetchRecords(
  workspace: string
): Promise<FinanceRecord[]> {
  const res = await fetch(`${API_URL}/records?workspace=${workspace}`);
  if (!res.ok) throw new Error("Failed to fetch records");
  return await res.json();
}

export async function createRecord(
  record: FinanceRecord
): Promise<FinanceRecord> {
  const res = await fetch(`${API_URL}/records`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(record),
  });
  if (!res.ok) throw new Error("Failed to create record");
  return await res.json();
}

export async function updateRecord(
  id: number,
  record: FinanceRecord
): Promise<FinanceRecord> {
  const res = await fetch(`${API_URL}/records/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(record),
  });
  if (!res.ok) throw new Error("Failed to update record");
  return await res.json();
}

export async function deleteRecord(id: number): Promise<void> {
  const res = await fetch(`${API_URL}/records/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Failed to delete record");
}
