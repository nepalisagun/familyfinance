import { useEffect, useState } from "react";
import "./App.css";
import type { FinanceRecord } from "./api";
import { fetchRecords, createRecord, deleteRecord } from "./api";

const defaultRecord: Omit<FinanceRecord, "id"> = {
  date: "",
  type: "income",
  amount: 0,
  workspace: "personal",
};

function App() {
  const [workspace, setWorkspace] = useState<"personal" | "business">(
    "personal"
  );
  const [records, setRecords] = useState<FinanceRecord[]>([]);
  const [form, setForm] = useState<Omit<FinanceRecord, "id">>({
    ...defaultRecord,
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    setLoading(true);
    fetchRecords(workspace)
      .then(setRecords)
      .catch((e) => setError(e instanceof Error ? e.message : String(e)))
      .finally(() => setLoading(false));
  }, [workspace]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value, type } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]:
        type === "checkbox" ? (e.target as HTMLInputElement).checked : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      const rec = await createRecord({ ...form, workspace });
      setRecords((prev) => [...prev, rec]);
      setForm({ ...defaultRecord, workspace });
    } catch (e) {
      setError(e instanceof Error ? e.message : String(e));
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <h1>All-in-One Family & Business Financial Hub</h1>
      <div className="workspace-toggle">
        <button onClick={() => setWorkspace("personal")}>
          Personal Workspace
        </button>
        <button onClick={() => setWorkspace("business")}>
          Business Workspace
        </button>
      </div>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Date:</label>
          <input
            type="date"
            name="date"
            value={form.date}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Type:</label>
          <select name="type" value={form.type} onChange={handleChange}>
            <option value="income">Income</option>
            <option value="expense">Expense</option>
            <option value="transfer">Transfer</option>
          </select>
        </div>
        <div className="form-group">
          <label>Amount:</label>
          <input
            type="number"
            name="amount"
            value={form.amount}
            onChange={handleChange}
            required
          />
        </div>
        {form.type === "income" && (
          <>
            <div className="form-group">
              <label>Source:</label>
              <input
                type="text"
                name="source"
                value={form.source || ""}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>Target Account:</label>
              <input
                type="text"
                name="targetAccount"
                value={form.targetAccount || ""}
                onChange={handleChange}
              />
            </div>
          </>
        )}
        {form.type === "expense" && (
          <>
            <div className="form-group">
              <label>Category:</label>
              <input
                type="text"
                name="category"
                value={form.category || ""}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>From Account:</label>
              <input
                type="text"
                name="fromAccount"
                value={form.fromAccount || ""}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>Tag:</label>
              <input
                type="text"
                name="tag"
                value={form.tag || ""}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>
                <input
                  type="checkbox"
                  name="isFixedExpense"
                  checked={!!form.isFixedExpense}
                  onChange={handleChange}
                />
                Is this a fixed expense?
              </label>
            </div>
          </>
        )}
        {form.type === "transfer" && (
          <>
            <div className="form-group">
              <label>From Account:</label>
              <input
                type="text"
                name="fromAccount"
                value={form.fromAccount || ""}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>To Account:</label>
              <input
                type="text"
                name="toAccount"
                value={form.toAccount || ""}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label>Frequency:</label>
              <select
                name="frequency"
                value={form.frequency || ""}
                onChange={handleChange}
              >
                <option value="">Select</option>
                <option value="one-time">One-Time</option>
                <option value="weekly">Weekly</option>
                <option value="bi-weekly">Bi-Weekly</option>
                <option value="monthly">Monthly</option>
              </select>
            </div>
          </>
        )}
        <button type="submit" disabled={loading}>
          Add Record
        </button>
      </form>
      {error && <div className="summary">Error: {error}</div>}
      <table>
        <thead>
          <tr>
            <th>Date</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Source/Category</th>
            <th>Account</th>
            <th>Frequency/Fixed</th>
            <th>Tag</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {records.map((rec) => (
            <tr key={rec.id}>
              <td>{rec.date}</td>
              <td>{rec.type}</td>
              <td>{rec.amount}</td>
              <td>{rec.source || rec.category || "-"}</td>
              <td>
                {rec.targetAccount || rec.fromAccount || rec.toAccount || "-"}
              </td>
              <td>
                {rec.frequency || (rec.isFixedExpense ? "Fixed" : "-") || "-"}
              </td>
              <td>{rec.tag || "-"}</td>
              <td>
                <button
                  onClick={async () => {
                    setLoading(true);
                    try {
                      if (rec.id) await deleteRecord(rec.id);
                      setRecords((prev) => prev.filter((r) => r.id !== rec.id));
                    } catch (e) {
                      setError(e instanceof Error ? e.message : String(e));
                    } finally {
                      setLoading(false);
                    }
                  }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
