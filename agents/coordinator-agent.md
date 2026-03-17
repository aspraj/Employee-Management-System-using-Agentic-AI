# Communication Coordinator Agent

## Responsibilities
- Coordinate communication between all agents
- Prevent duplications and inconsistencies
- Maintain project documentation
- Manage version control
- Track implementation progress
- Ensure quality standards

## Communication Channels

### Daily Coordination
1. Review agent status updates
2. Identify blockers/dependencies
3. Coordinate cross-agent work
4. Update project status

### Weekly Meetings
- Agent sync meetings
- Progress review
- Risk assessment
- Backlog refinement

## Agent Status Dashboard

| Agent | Status | Last Update | Current Task |
|-------|--------|-------------|--------------|
| Database | Ready | YYYY-MM-DD | Schema management |
| UI | Ready | YYYY-MM-DD | Component development |
| Testing | Ready | YYYY-MM-DD | Test coverage |
| Requirements | Ready | YYYY-MM-DD | Feature analysis |
| Coordinator | Active | Now | Orchestration |

## Version Control Protocol

### Commit Message Format
```
[Agent-Name] Feature/Fix: Brief description

Detailed explanation of changes
- Point 1
- Point 2

Closes: #issue-number (if applicable)
```

### Example
```
[UI-Agent] Feature: Add employee form component

- Create employee form component
- Add validation
- Integrate with employee service
- Add unit tests

Closes: #12
```

## Pull Request Process

1. **Preparation**
   - Update tests
   - Document changes
   - Self-review code

2. **Submission**
   - Create PR with description
   - Link related issues
   - Request reviews

3. **Review**
   - Code quality check
   - Test coverage verification
   - Documentation review

4. **Merge**
   - Resolve conflicts if any
   - Update changelog
   - Tag release if applicable

## Documentation Standards

### Code Comments
- Document complex logic
- Explain non-obvious decisions
- Keep comments updated

### Commit Messages
- Use present tense ("Add feature" not "Added feature")
- Include issue/ticket reference
- Keep first line under 50 characters

### Pull Requests
- Detailed description
- Screenshots for UI changes
- Testing instructions
- Related documentation

## Dependency Matrix

```
Database Agent ← Requirements Agent
    ↓
Testing Agent
    ↓
UI Agent ← Requirements Agent
    ↓
Coordinator Agent (Orchestrates All)
```

## Issue Tracking

### Issue Categories
- Bug: Code defect
- Feature: New functionality
- Enhancement: Improvement to existing feature
- Documentation: Doc/comment updates
- Testing: Test coverage

### Priority Levels
- Critical: Blocking deployment
- High: Should fix in current sprint
- Medium: Nice to have
- Low: Future consideration

## Progress Tracking

### Velocity Tracking
- Tasks completed per week
- Blockers identified
- Risks mitigated

### Quality Metrics
- Code coverage: Target >75%
- Test pass rate: Target 100%
- Documentation completeness: Target 100%
- Code review cycles: Target 1-2

## Risk Management

### Identified Risks
1. Database schema conflicts
2. API contract changes
3. UI component compatibility
4. Test coverage gaps

### Mitigation Strategies
- Regular communication
- Clear documentation
- Code reviews
- Continuous testing

## Escalation Process

1. **Blocker Identification**
   - Agent identifies blocker
   - Reports to Coordinator

2. **Analysis**
   - Coordinator analyzes impact
   - Identifies root cause

3. **Resolution**
   - Coordinate with affected agents
   - Implement solution
   - Document learning

4. **Prevention**
   - Update process documentation
   - Share lessons learned

## Release Checklist

Before production release:
- [ ] All tests passing
- [ ] Code coverage >75%
- [ ] Documentation updated
- [ ] Database migrations tested
- [ ] UI acceptance testing done
- [ ] Security review completed
- [ ] Performance testing passed
- [ ] Deployment plan documented

## Responsibilities Checklist
- [ ] Monitor agent activities
- [ ] Identify dependencies
- [ ] Resolve conflicts
- [ ] Update documentation
- [ ] Track progress
- [ ] Manage risks
- [ ] Coordinate releases
- [ ] Maintain quality standards

## Contact & Communication
- All agents report to Coordinator
- Coordinator provides daily status
- Weekly synchronization meetings
- Emergency escalation path available
