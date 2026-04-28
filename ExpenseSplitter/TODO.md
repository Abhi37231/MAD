# Complete ExpenseSplitter - Theme + Full Functions Plan (Approved)

## Progress Tracker

### 1. Perfect Theme [x]  (light/dark colors + full Material3 attrs)
   - Create values-night/colors.xml
   - Update themes.xml (light/dark)
   - Update styles.xml if needed

### 2. Complete DB Schema & Functions [x]  (members table, per-group expense/balance, addMember/getMembers)
   - Edit DBHelper.java: Add members table, update methods

### 3. Update Existing Activities/Layouts [x]  (AddGroup members, AddExpense group spinner, Main FAB balance)
   - MainActivity.java + activity_main.xml (balance FAB)
   - AddExpenseActivity.java + activity_add_expense.xml (group select)
   - AddGroupActivity.java (add members)
   - BalanceActivity.java (per-group)

### 4. Add Group Detail Features [ ]
   - Create GroupDetailActivity.java + layout
   - item_member.xml, item_expense.xml if needed

### 5. Update GroupAdapter [x]  (click to per-group balance)
   - Add click listener to open GroupDetail

### 6. Models & Misc [ ]
   - Member.java if needed
   - Update TODO_UI.md

### 7. Test & Build [ ]
   - Clean/rebuild
   - Test full flow on emulator
